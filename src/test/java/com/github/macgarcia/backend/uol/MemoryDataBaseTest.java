package com.github.macgarcia.backend.uol;

import com.github.macgarcia.backend.uol.model.Grupo;
import com.github.macgarcia.backend.uol.model.Usuario;
import com.github.macgarcia.backend.uol.repository.MemoryDataBase;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author macgarcia
 */
public class MemoryDataBaseTest {

    private static MemoryDataBase memoryDataBase = new MemoryDataBase();
    private static Usuario usuarioTest;

    @BeforeClass
    public static void config() {
        usuarioTest = new Usuario();
        usuarioTest.setNome("Teste01");
        usuarioTest.setEmail("teste@teste.com.br");
        usuarioTest.setTelefone("123456");
        usuarioTest.setCodinome("Codinome Teste");
        usuarioTest.setGrupo(Grupo.VINGADORES);
        memoryDataBase.persiste(usuarioTest);
    }

    @Test
    public void verificarUsuarioPersistido() {
        Usuario usuario = memoryDataBase.getUsuario("Codinome Teste");
        Assert.assertEquals("Teste01", usuario.getNome());
    }

    @Test
    public void verificarBuscarUsauarios() {
        Assert.assertEquals(1, memoryDataBase.getAll().size());
    }

    @Test
    public void verificarExistenciaDeUsuario() {
        boolean existeCadastro = memoryDataBase.existeCadastro(usuarioTest.getCodinome());
        Assert.assertTrue(existeCadastro);
    }

    @Test
    public void verificarUsuarioNaoExiste() {
        boolean existeCadastro = memoryDataBase.existeCadastro("OlaMundo");
        Assert.assertFalse(existeCadastro);
    }

    @Test
    public void removerUsuario() {
        memoryDataBase.removerUsuario(usuarioTest.getCodinome());
        boolean existeCadastro = memoryDataBase.existeCadastro(usuarioTest.getCodinome());
        Assert.assertFalse(existeCadastro);
    }

}
