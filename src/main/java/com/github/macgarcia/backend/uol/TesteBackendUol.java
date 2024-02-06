package com.github.macgarcia.backend.uol;

import com.github.macgarcia.backend.uol.repository.MemoryDataBase;
import com.github.macgarcia.backend.uol.service.BackEndUolService;
import static spark.Spark.get;
import static spark.Spark.post;

/**
 *
 * @author macgarcia
 */
public class TesteBackendUol {

    public static void main(String[] args) {

        MemoryDataBase db = new MemoryDataBase();
        BackEndUolService service = new BackEndUolService();

        get("/lista", (req, res) -> service.getAll(req, res, db).body());
        post("/cadastro", (req, res) -> service.cadastrarJogador(req, res, db).body());
    }
}
