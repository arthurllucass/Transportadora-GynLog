
package visao;

import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import modelos.classes.StatusVeiculo;
import modelos.classes.TipoDeVeiculos;
import persistencia.TipoDeVeiculoDAO;

public class TelaBuscarVeiculoMovimentacoes extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(TelaBuscarVeiculoMovimentacoes.class.getName());
    private String idSelecionado = null;
    private JTextField campoDestino; //campo que recebe o ID selecionado

    
    public TelaBuscarVeiculoMovimentacoes() {
        initComponents();
        setLocationRelativeTo(null);
        carregarTabelaVeiculosAtivos();
        configurarCliqueNaTabela();
        jTableCadastroVeiculos.setDefaultEditor(Object.class, null); //metodo para travar a ediçao na table
    }

    public TelaBuscarVeiculoMovimentacoes(JTextField campoDestino) {
        initComponents();
        this.campoDestino = campoDestino;
        setLocationRelativeTo(null);
        carregarTabelaVeiculosAtivos();
        configurarCliqueNaTabela();
        jTableCadastroVeiculos.setDefaultEditor(Object.class, null);
    }
    
    public void carregarTabelaVeiculosAtivos() {
    try {
        TipoDeVeiculoDAO dao = new TipoDeVeiculoDAO();

        ArrayList<TipoDeVeiculos> lista = dao.buscarPorStatus(StatusVeiculo.ATIVO);

        DefaultTableModel model = (DefaultTableModel) jTableCadastroVeiculos.getModel();
        model.setRowCount(0);

        for (TipoDeVeiculos veiculos : lista) {
            model.addRow(new Object[]{
                veiculos.getIdVeiculo(),
                veiculos.getPlaca(),
                veiculos.getMarca(),          
                veiculos.getAnoDeFabricacao()
            });
        }

    } catch (Exception erro) {
        JOptionPane.showMessageDialog(null, erro.getMessage());
    }
}

    public void configurarCliqueNaTabela() {
        jTableCadastroVeiculos.addMouseListener(new java.awt.event.MouseAdapter() {
            
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if (evt.getClickCount() == 2) { // Duplo clique
                    int linha = jTableCadastroVeiculos.getSelectedRow();
                    if (linha >= 0) {
                        String id = jTableCadastroVeiculos.getValueAt(linha, 0).toString();
                        campoDestino.setText(id);
                        dispose();
                    }
                }
            }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTableCadastroVeiculos = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Lista de veículos");
        setResizable(false);

        jTableCadastroVeiculos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID", "Placa", "Marca", "Modelo", "Ano"
            }
        ));
        jScrollPane1.setViewportView(jTableCadastroVeiculos);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 600, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new TelaBuscarVeiculoMovimentacoes().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableCadastroVeiculos;
    // End of variables declaration//GEN-END:variables
}
