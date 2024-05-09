import java.util.List;

public class Cadastro {
    
    public Cadastro(){}

    public Cadastro(String nomeCompleto, String cpf){

    }

    public CadastroVO efetuaCadastro(int id, String cpf,
        String nomeCompleto, String genero, String idade,
        String endereco, String telefone){

        CadastroVO vo = new CadastroVO();
        vo.setId(id);
        vo.setCpf(cpf);
        vo.setNomeCompleto(nomeCompleto);
        vo.setGenero(genero);
        vo.setIdade(idade);
        vo.setEndereco(endereco);
        vo.setTelefone(telefone);

        return vo;
    }

    public List<CadastroVO> deletarCadastro(List<CadastroVO> lista, String cpf){

        List<CadastroVO> listaModificada = lista;
        CadastroVO vo = null;

        for (int i = 0; i < lista.size(); i++){
            vo = lista.get(i);

            if(cpf != null && !cpf.equals("")){
                if(cpf.equals(vo.getCpf())){
                    listaModificada.remove(i);
                }
            }
        }
        
        return listaModificada;
    }
}
