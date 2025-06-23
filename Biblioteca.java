package biblioteca;

import java.sql.SQLException;
import java.util.List;

public class Biblioteca {

    
    public static void main(String[] args) {
       UsuarioDAO usuarioDAO = new UsuarioDAO();
         try{
         int idBuscado = 7;
         Usuario usuario = usuarioDAO.buscarUsuarioPorId(idBuscado);
         
         
        if(usuario != null){
         System.out.println("encontrei!!");
         System.out.println("ID: "+usuario.getId());
         System.out.println("nome: "+usuario.getNome());
         System.out.println("email: "+usuario.getEmail());
         System.out.println("telefone: "+usuario.getTelefone());
         System.out.println("tipo: "+usuario.getTipo_usuario());
         System.out.println("------------------------------------");
         
        }else{
          System.out.println("usuario com id "+idBuscado+"nao achei");         
    }
}
catch(SQLException e){
   System.out.println("erro: "+e.getMessage());  
}
    }
}
