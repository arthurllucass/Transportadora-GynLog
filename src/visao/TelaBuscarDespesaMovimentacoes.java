package visao;

import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import modelos.classes.TipoDeDespesa;
import persistencia.TipoDeDespesasDAO;

public class TelaBuscarDespesaMovimentacoes extends javax.swing.JFrame {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(TelaBuscarDespesaMovimentacoes.class.getName());
    private String idSelecionado = null;
    private JTextField campoDestino; //campo que recebe o ID selecionado

    public TelaBuscarDespesaMovimentacoes() {
        initComponents();
        setLocationRelativeTo(null);
        carregarTabelaDespesas();
        configurarCliqueNaTabela();
        jTableTelaDespesas.setDefaultEditor(Object.class, null); //metodo para travar a ediçao na table
    }

    public TelaBuscarDespesaMovimentacoes(JTextField campoDestino) {
        initComponents();
        this.campoDestino = campoDestino;
        setLocationRelativeTo(null);
        carregarTabelaDespesas();
        configurarCliqueNaTabela();
        jTableTelaDespesas.setDefaultEditor(Object.class, null);
    }

    public void carregarTabelaDespesas() {
        try {
            TipoDeDespesasDAO dao = new TipoDeDespesasDAO();

            ArrayList<TipoDeDespesa> lista = dao.listaDeTiposDeDespesas();

            DefaultTableModel model = (DefaultTableModel) jTableTelaDespesas.getModel();
            model.setRowCount(0);

            for (TipoDeDespesa tipoDespesa : lista) {
                model.addRow(new Object[]{
                    tipoDespesa.getIdTipoDeDespesa(),
                    tipoDespesa.getDescricao()
                });
            }
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, erro.getMessage());
        }
    }

    public void configurarCliqueNaTabela() {
        jTableTelaDespesas.addMouseListener(new java.awt.event.MouseAdapter() {

            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if (evt.getClickCount() == 2) { // Duplo clique
                    int linha = jTableTelaDespesas.getSelectedRow();
                    if (linha >= 0) {
                        String id = jTableTelaDespesas.getValueAt(linha, 0).toString();
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
        jTableTelaDespesas = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTableTelaDespesas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "ID", "Descrição"
            }
        ));
        jScrollPane1.setViewportView(jTableTelaDespesas);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 600, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
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
        java.awt.EventQueue.invokeLater(() -> new TelaBuscarDespesaMovimentacoes().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableTelaDespesas;
    // End of variables declaration//GEN-END:variables
}
