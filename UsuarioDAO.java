package biblioteca;

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {
    private Connection connection;
    
    public UsuarioDAO(){
        this.connection = new ConnectionFactory().conectaBD();
    }
    
    
   public void criaUsuario(Usuario usuario) throws SQLException{
     String sql = "INSERT INTO tb_usuarios (nome, email, telefone,"
             + "tipo_usuario) VALUES (?,?,?,?) ";
     
     PreparedStatement pstm = null;
     try{
         pstm = connection.prepareStatement(sql);
         pstm.setString(1, usuario.getNome());
         pstm.setString(2, usuario.getEmail());
         pstm.setString(3, usuario.getTelefone());
         pstm.setString(4, usuario.getTipo_usuario());

         pstm.executeUpdate();
         System.out.println("deu certo!");
             }catch(SQLException e){
               System.out.println("deu errado "+e.getMessage());
              }   
     finally{
       if (pstm != null)pstm.close();
             }
   }
    
public List<Usuario> listarUsuarios()throws SQLException{
      List<Usuario> lista = new ArrayList<>();
      String sql = "SELECT * FROM usuarios";
      
    PreparedStatement pstm = null;
    ResultSet rs = null;
    

   try{
      pstm = connection.prepareStatement(sql);
      rs = pstm.executeQuery();
      while(rs.next()){
        Usuario usuario = new Usuario(); 
        usuario.setId(rs.getInt("id"));
        usuario.setNome(rs.getString("nome"));
        usuario.setEmail(rs.getString("email"));
        usuario.setTelefone(rs.getString("telefone"));
        usuario.setTipo_usuario(rs.getString("tipo_usuario"));
        
  lista.add(usuario);
      }
      }catch (SQLException e){
      System.out.println("erro "+e.getMessage());
        }finally{
                if(rs != null)rs.close();
                if(pstm != null)pstm.close();
                }
    return lista;
    
   }
   
public Usuario buscarUsuarioPorId(int id) throws SQLException{
    String sql = "SELECT * FROM usuarios WHERE id=? ";
    

      PreparedStatement pstm = null;
      ResultSet rs = null;
      Usuario usuario = null;
      try{
        pstm = connection.prepareStatement(sql);
        pstm.setInt(1, id);
        rs = pstm.executeQuery();
        if(rs.next()){
           usuario = new Usuario();
           usuario.setId(rs.getInt(id));
           usuario.setNome(rs.getString("nome"));
           usuario.setEmail(rs.getString("email"));
           usuario.setTelefone(rs.getString("telefone"));
           usuario.setTipo_usuario(rs.getString("tipo_usuario"));
        }
      }catch(SQLException e){
         System.out.println("erro "+e.getMessage());
      }finally{
          if(rs != null)rs.close();
          if(pstm != null)pstm.close();
      }
      return usuario;
}
      
public void atualizaUsuario (Usuario usuario) throws SQLException{
        String sql = "UPDATE usuarios SET nome = ?, email = ?," 
                + "telefone = ?, tipo_usuario = ? ";
        
PreparedStatement pstm = null;

try{
    pstm = connection.prepareStatement(sql);
    pstm.setString(1,usuario.getNome());
    pstm.setString(2,usuario.getEmail());
    pstm.setString(3, usuario.getTelefone());
    pstm.setString(4,usuario.getTipo_usuario());
    pstm.setInt(5, usuario.getId());
    
int linhasAfetadas = pstm.executeUpdate();
if(linhasAfetadas > 0) {
    System.out.println("att com sucesso");
}else{
  System.out.println("usuario nao encontrado");
}

        }catch(SQLException e){
             System.out.println("erro  "+e.getMessage());
        }finally{
          if (pstm != null)pstm.close();
        }
}
}



        
      
  

