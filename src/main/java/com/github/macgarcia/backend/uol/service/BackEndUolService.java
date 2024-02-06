package com.github.macgarcia.backend.uol.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.github.macgarcia.backend.uol.dto.ResponseDto;
import com.github.macgarcia.backend.uol.model.Grupo;
import com.github.macgarcia.backend.uol.model.Usuario;
import com.github.macgarcia.backend.uol.regra.ChamadaExterna;
import com.github.macgarcia.backend.uol.regra.ChamadaExternaLigaDaJustica;
import com.github.macgarcia.backend.uol.regra.ChamadaExternaVingadores;
import com.github.macgarcia.backend.uol.repository.MemoryDataBase;
import com.github.macgarcia.backend.uol.vo.ligadajustica.LigaDaJustica;
import com.github.macgarcia.backend.uol.vo.vingadores.Heroi;
import com.github.macgarcia.backend.uol.vo.vingadores.VingadoresWapper;
import java.util.List;
import java.util.Optional;
import spark.Request;
import spark.Response;

/**
 *
 * @author macgarcia
 */
public class BackEndUolService {

    private final ObjectMapper mapper;

    private Usuario usuario;
    private VingadoresWapper vingadoresWapper;
    private LigaDaJustica ligaDaJustica;
    private String dadosCodinomes;

    public BackEndUolService() {
        mapper = new ObjectMapper();
    }

    public Response cadastrarJogador(Request req, Response res, MemoryDataBase db) throws JsonProcessingException {

        Optional<String> umCodinome;
        usuario = mapper.readValue(req.body(), Usuario.class);

        if (Grupo.VINGADORES.equals(usuario.getGrupo())) {
            chamadaExterna(new ChamadaExternaVingadores());
            processarRetornoVingadores();
            umCodinome = getCodinomeVingadores(db);
        } else {
            chamadaExterna(new ChamadaExternaLigaDaJustica());
            processarRetornoLigaDaJustica();
            umCodinome = getCodinomeLigaDaJustica(db);
        }

        if (umCodinome.isPresent()) {
            usuario.setCodinome(umCodinome.get());
            db.persiste(usuario);
            montarResposta(res, 201, "Dados registrados com sucesso");
        } else {
            montarResposta(res, 406, "Não é possivel mais haver cadastro nesta equipe...");
        }
        fimDoProcessamento();
        return res;
    }

    private void chamadaExterna(ChamadaExterna call) {
        dadosCodinomes = call.getDados();
    }

    private void processarRetornoVingadores() throws JsonProcessingException {
        vingadoresWapper = mapper.readValue(dadosCodinomes, new TypeReference<VingadoresWapper>() {
        });
    }

    private void processarRetornoLigaDaJustica() throws JsonProcessingException {
        XmlMapper xmlMapper = new XmlMapper();
        ligaDaJustica = xmlMapper.readValue(dadosCodinomes, LigaDaJustica.class);
    }

    private Optional<String> getCodinomeVingadores(MemoryDataBase db) {
        for (Heroi h : vingadoresWapper.getVingadores()) {
            boolean existeCadastro = db.existeCadastro(h.getCodinome());
            if (!existeCadastro) {
                return Optional.of(h.getCodinome());
            }
        }
        return Optional.empty();
    }

    private Optional<String> getCodinomeLigaDaJustica(MemoryDataBase db) {
        for (String codinome : ligaDaJustica.getCodinomes()) {
            boolean existeCadastro = db.existeCadastro(codinome);
            if (!existeCadastro) {
                return Optional.of(codinome);
            }
        }
        return Optional.empty();
    }

    private void montarResposta(Response res, int code, String mensagem) throws JsonProcessingException {
        ResponseDto dto = new ResponseDto(code, mensagem);
        final String json = mapper.writeValueAsString(dto);
        res.status(code);
        res.header("Content-Type", "application/json");
        res.header("charset", "utf-8");
        res.body(json);
    }

    private void fimDoProcessamento() {
        usuario = null;
        vingadoresWapper = null;
        ligaDaJustica = null;
        dadosCodinomes = null;
    }

    /*------------------------------------*/
    public Response getAll(Request req, Response res, MemoryDataBase db) throws JsonProcessingException {
        List<Usuario> all = db.getAll();
        String json = mapper.writeValueAsString(all);
        res.header("Content-Type", "application/json");
        res.header("charset", "utf-8");
        res.status(200);
        res.body(json);
        return res;
    }

}
