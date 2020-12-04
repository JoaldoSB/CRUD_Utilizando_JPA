package prjVendaDAO;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JOptionPane;

public class Principal {
	
	public static void main(String[] args) {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("vendas");
		EntityManager em = emf.createEntityManager();

		String descricaoOpcao = 
				  "1 - Cadastrar \n"
				+ "2 - Editar\n"
				+ "3 - Remover\n"
				+ "4 - Sair";
		
		int opcao = Integer.parseInt(JOptionPane.showInputDialog(descricaoOpcao));
		
		switch (opcao) {
		case 1:
			String descricao = JOptionPane.showInputDialog("Informe a descrição");
			String valor = JOptionPane.showInputDialog("Informe o valor");
			String valorCompra = JOptionPane.showInputDialog("Informe o valor de compra");
			
			try {
			
			Produto p = new Produto();
			p.setDescricao(descricao);
			p.setValor(Double.parseDouble(valor));
			p.setValorCompra(Double.parseDouble(valorCompra));
			
		
			
			em.getTransaction().begin();
			em.persist(p);
			em.getTransaction().commit();
			
			JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso");
			} catch(Exception e) {
				
				JOptionPane.showMessageDialog(null, "Erro ao cadastrar o produto. Verifique os dados e tente novamente");
			}
			break;
		
		case 2:
			String idProduto2 = JOptionPane.showInputDialog("Informe o ID do Produto");
			String descricao2 = JOptionPane.showInputDialog("Informe a descrição");
			String valor2 = JOptionPane.showInputDialog("Informe o valor");
			String valorCompra2 = JOptionPane.showInputDialog("Informe o valor de compra");

			try {
				
				int id = Integer.parseInt(idProduto2);
				Produto p = em.find(Produto.class, id);
				p.setId(id);
				p.setDescricao(descricao2);
				p.setValor(Double.parseDouble(valor2));
				p.setValorCompra(Double.parseDouble(valorCompra2));
				
				em.getTransaction().begin();
				em.persist(p);
				em.getTransaction().commit();
				
				JOptionPane.showMessageDialog(null, "Produto editado com sucesso");
			}catch(Exception e) {
				
				JOptionPane.showMessageDialog(null, "Erro ao editar o produto. Verifique os dados e tente novamente");
			}

			
			break;
			
		case 3:
			String idProduto = JOptionPane.showInputDialog("Informe o ID do Produto");
			
			try {
				Produto p = new Produto();
				int id = Integer.parseInt(idProduto);
				p.setId(id);
				p = em.find(Produto.class, id);
				
				em.getTransaction().begin();
				em.remove(p);
				em.getTransaction().commit();
				
				JOptionPane.showMessageDialog(null, "Produto removido com sucesso");
			}catch(Exception e) {
				
				JOptionPane.showMessageDialog(null, "Erro ao deletar o produto. Verifique os dados e tente novamente");
			}
			
			break;
		
		case 4:
			
			JOptionPane.showMessageDialog(null, "Obrigado e volte sempre.");
			break;
		default:
			
			JOptionPane.showMessageDialog(null, "Opção Invalida!");
			break;
		}
		
	}
	
}
