
package visao;

import visao.TelaRelatorios;
import visao.TelaTipoDeDespesas;
import visao.TelaTipoDeVeiculo;

public class TelaPrincipal extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(TelaPrincipal.class.getName());


    public TelaPrincipal() {
        initComponents();
        setLocationRelativeTo(null);
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDesktopPane1 = new javax.swing.JDesktopPane();
        jButtonTelaVeiculos = new javax.swing.JButton();
        jButtonTelaTipoDeDespesa = new javax.swing.JButton();
        jButtonTelaMovimentacoes = new javax.swing.JButton();
        jButtonRelatorios = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButtonTelaVeiculos.setText("Tela de Veiculos");
        jButtonTelaVeiculos.addActionListener(this::jButtonTelaVeiculosActionPerformed);

        jButtonTelaTipoDeDespesa.setText("Tela Tipo de Despesa");
        jButtonTelaTipoDeDespesa.addActionListener(this::jButtonTelaTipoDeDespesaActionPerformed);

        jButtonTelaMovimentacoes.setText("Tela Movimentações");
        jButtonTelaMovimentacoes.addActionListener(this::jButtonTelaMovimentacoesActionPerformed);

        jButtonRelatorios.setText("Tela Relatórios");
        jButtonRelatorios.addActionListener(this::jButtonRelatoriosActionPerformed);

        jDesktopPane1.setLayer(jButtonTelaVeiculos, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jButtonTelaTipoDeDespesa, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jButtonTelaMovimentacoes, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jButtonRelatorios, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jDesktopPane1Layout = new javax.swing.GroupLayout(jDesktopPane1);
        jDesktopPane1.setLayout(jDesktopPane1Layout);
        jDesktopPane1Layout.setHorizontalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDesktopPane1Layout.createSequentialGroup()
                .addContainerGap(364, Short.MAX_VALUE)
                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDesktopPane1Layout.createSequentialGroup()
                        .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButtonTelaMovimentacoes)
                            .addComponent(jButtonTelaTipoDeDespesa))
                        .addGap(287, 287, 287))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDesktopPane1Layout.createSequentialGroup()
                        .addComponent(jButtonTelaVeiculos)
                        .addGap(310, 310, 310))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDesktopPane1Layout.createSequentialGroup()
                        .addComponent(jButtonRelatorios)
                        .addGap(322, 322, 322))))
        );
        jDesktopPane1Layout.setVerticalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDesktopPane1Layout.createSequentialGroup()
                .addGap(138, 138, 138)
                .addComponent(jButtonTelaVeiculos)
                .addGap(18, 18, 18)
                .addComponent(jButtonTelaTipoDeDespesa)
                .addGap(26, 26, 26)
                .addComponent(jButtonTelaMovimentacoes)
                .addGap(33, 33, 33)
                .addComponent(jButtonRelatorios)
                .addContainerGap(326, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jDesktopPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jDesktopPane1)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonTelaMovimentacoesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonTelaMovimentacoesActionPerformed
        TelaMovimentacao telaMovimentacao = new TelaMovimentacao();
        telaMovimentacao.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButtonTelaMovimentacoesActionPerformed

    private void jButtonTelaVeiculosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonTelaVeiculosActionPerformed
        TelaTipoDeVeiculo telaVeiculo = new TelaTipoDeVeiculo();
        telaVeiculo.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButtonTelaVeiculosActionPerformed

    private void jButtonTelaTipoDeDespesaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonTelaTipoDeDespesaActionPerformed
        TelaTipoDeDespesas telaTipoDespesas = new TelaTipoDeDespesas();
        telaTipoDespesas.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButtonTelaTipoDeDespesaActionPerformed

    private void jButtonRelatoriosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRelatoriosActionPerformed
        TelaRelatorios telarelatorios = new TelaRelatorios();
        telarelatorios.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButtonRelatoriosActionPerformed

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
        java.awt.EventQueue.invokeLater(() -> new TelaPrincipal().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonRelatorios;
    private javax.swing.JButton jButtonTelaMovimentacoes;
    private javax.swing.JButton jButtonTelaTipoDeDespesa;
    private javax.swing.JButton jButtonTelaVeiculos;
    private javax.swing.JDesktopPane jDesktopPane1;
    // End of variables declaration//GEN-END:variables
}
