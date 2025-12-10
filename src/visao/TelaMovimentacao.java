package visao;

import controle.MovimentacaoController;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelos.classes.Movimentacao;
import modelos.classes.TipoDeDespesa;
import persistencia.TipoDeDespesasDAO;


public class TelaMovimentacao extends javax.swing.JFrame {
    
    private MovimentacaoController movimentacaoController = new MovimentacaoController(); /* coloquei como atributo da tela para que nao seja necessario 
    criar um controller toda vez que apertar os botoes*/
    private int[] ids;
    
    public TelaMovimentacao() {
        initComponents();
        carregarComboTipoDespesa();
        this.setLocationRelativeTo(null);
        LocalDate hoje = LocalDate.now();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        jFormattedTextFieldData.setText(hoje.format(formato));
        jTextFieldIdVeiculo.setEditable(false);
        jTableMovimentacoes.setDefaultEditor(Object.class, null);
        try {
            java.awt.Image icone = javax.imageio.ImageIO.read(getClass().getResource("/imagens/40X40.png"));
            this.setIconImage(icone);
        } catch (Exception e) {
           JOptionPane.showMessageDialog(null,e.getMessage());
        }
        try {
            if (movimentacaoController.listarMovimentacoes().isEmpty()) {
                jButtonRemover.setEnabled(false);
            }
        } catch (Exception erro) {
            jButtonRemover.setEnabled(false);
        }
        
        atualizarTabela();
    }

    @SuppressWarnings("unchecked")
    
    public void atualizarTabela(){
        DefaultTableModel model = (DefaultTableModel) jTableMovimentacoes.getModel();
        model.setRowCount(0);

        try {
            ArrayList<Movimentacao> listaDeMovimentacoes = movimentacaoController.listarMovimentacoes();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            String[] saida = new String[6];

            for (Movimentacao movimentacao : listaDeMovimentacoes) {
                saida[0] = movimentacao.getIdMovimentacao() + "";
                saida[1] = movimentacao.getIdVeiculo() + "";
                saida[2] = movimentacao.getIdTipoDespesa() + "";
                saida[3] = movimentacao.getDescricao();
                saida[4] = sdf.format(movimentacao.getDataMovimentacao()) + "";
                saida[5] = String.format("R$ %.2f", movimentacao.getValor());

                model.addRow(saida);
            }
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, erro.getMessage());
        }
    }
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTextFieldIdVeiculo = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jFormattedTextFieldData = new javax.swing.JFormattedTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jFormattedTextFieldValor = new javax.swing.JFormattedTextField();
        jTextFieldDescricao = new javax.swing.JTextField();
        jButtonCadastrar = new javax.swing.JButton();
        jButtonVoltar = new javax.swing.JButton();
        jButtonRemover = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jButtonProcurar = new javax.swing.JButton();
        jButtonProcurarIDDespesa = new javax.swing.JButton();
        jComboBoxTipoDeDespesa = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableMovimentacoes = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();

        jButton1.setText("jButton1");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Movimentações de Despesa");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setPreferredSize(new java.awt.Dimension(680, 480));
        setResizable(false);
        setSize(new java.awt.Dimension(900, 600));

        jPanel2.setBackground(new java.awt.Color(0, 0, 51));
        jPanel2.setForeground(new java.awt.Color(255, 255, 255));

        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("ID VEÍCULO");

        jTextFieldIdVeiculo.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextFieldIdVeiculo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldIdVeiculoActionPerformed(evt);
            }
        });

        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("TIPO DE DESPESA");

        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("DATA ");
        jLabel3.setToolTipText("");

        try {
            jFormattedTextFieldData.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jFormattedTextFieldData.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("VALOR");

        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("DESCRIÇÃO");

        jFormattedTextFieldValor.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jFormattedTextFieldValor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFormattedTextFieldValorActionPerformed(evt);
            }
        });

        jTextFieldDescricao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldDescricaoActionPerformed(evt);
            }
        });

        jButtonCadastrar.setBackground(new java.awt.Color(102, 102, 102));
        jButtonCadastrar.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        jButtonCadastrar.setForeground(new java.awt.Color(255, 255, 255));
        jButtonCadastrar.setText("CADASTRAR");
        jButtonCadastrar.setToolTipText("");
        jButtonCadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCadastrarActionPerformed(evt);
            }
        });

        jButtonVoltar.setText("Voltar");
        jButtonVoltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonVoltarActionPerformed(evt);
            }
        });

        jButtonRemover.setBackground(new java.awt.Color(102, 102, 102));
        jButtonRemover.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        jButtonRemover.setForeground(new java.awt.Color(255, 255, 255));
        jButtonRemover.setText("REMOVER");
        jButtonRemover.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRemoverActionPerformed(evt);
            }
        });

        jButtonProcurar.setBackground(new java.awt.Color(0, 0, 51));
        jButtonProcurar.setFont(new java.awt.Font("Arial Black", 0, 10)); // NOI18N
        jButtonProcurar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/LUPA BRANCA - 20X20.png"))); // NOI18N
        jButtonProcurar.setBorder(null);
        jButtonProcurar.setBorderPainted(false);
        jButtonProcurar.setContentAreaFilled(false);
        jButtonProcurar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonProcurarActionPerformed(evt);
            }
        });

        jButtonProcurarIDDespesa.setBackground(new java.awt.Color(0, 0, 51));
        jButtonProcurarIDDespesa.setFont(new java.awt.Font("Arial Black", 0, 10)); // NOI18N
        jButtonProcurarIDDespesa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/LUPA BRANCA - 20X20.png"))); // NOI18N
        jButtonProcurarIDDespesa.setBorder(null);
        jButtonProcurarIDDespesa.setContentAreaFilled(false);
        jButtonProcurarIDDespesa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonProcurarIDDespesaActionPerformed(evt);
            }
        });

        jComboBoxTipoDeDespesa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxTipoDeDespesaActionPerformed(evt);
            }
        });

        jTableMovimentacoes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "ID VEÍCULO", "ID DESPESA", "DESCRIÇÃO", "DATA", "VALOR"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableMovimentacoes.setFocusable(false);
        jTableMovimentacoes.setGridColor(new java.awt.Color(0, 0, 255));
        jTableMovimentacoes.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(jTableMovimentacoes);
        if (jTableMovimentacoes.getColumnModel().getColumnCount() > 0) {
            jTableMovimentacoes.getColumnModel().getColumn(0).setResizable(false);
            jTableMovimentacoes.getColumnModel().getColumn(0).setPreferredWidth(10);
            jTableMovimentacoes.getColumnModel().getColumn(1).setResizable(false);
            jTableMovimentacoes.getColumnModel().getColumn(1).setPreferredWidth(40);
            jTableMovimentacoes.getColumnModel().getColumn(2).setResizable(false);
            jTableMovimentacoes.getColumnModel().getColumn(2).setPreferredWidth(40);
            jTableMovimentacoes.getColumnModel().getColumn(3).setPreferredWidth(150);
            jTableMovimentacoes.getColumnModel().getColumn(4).setResizable(false);
            jTableMovimentacoes.getColumnModel().getColumn(4).setPreferredWidth(60);
            jTableMovimentacoes.getColumnModel().getColumn(5).setResizable(false);
            jTableMovimentacoes.getColumnModel().getColumn(5).setPreferredWidth(60);
        }

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/logo 200 x 200.png"))); // NOI18N
        jLabel7.setText("jLabel7");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(62, 62, 62)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jFormattedTextFieldValor, javax.swing.GroupLayout.DEFAULT_SIZE, 129, Short.MAX_VALUE)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jTextFieldIdVeiculo)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonProcurar)
                                .addGap(30, 30, 30)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jComboBoxTipoDeDespesa, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(2, 2, 2)
                                        .addComponent(jButtonProcurarIDDespesa, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jFormattedTextFieldData, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                    .addComponent(jButtonCadastrar, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(jButtonRemover, javax.swing.GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE))
                                .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jTextFieldDescricao, javax.swing.GroupLayout.Alignment.LEADING)))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(96, 96, 96)
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButtonVoltar, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(5, 5, 5))))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 663, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(44, 44, 44)
                .addComponent(jLabel6))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButtonProcurar, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextFieldIdVeiculo, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jComboBoxTipoDeDespesa, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButtonProcurarIDDespesa, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jFormattedTextFieldValor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jFormattedTextFieldData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(13, 13, 13)
                        .addComponent(jLabel5)
                        .addGap(7, 7, 7)
                        .addComponent(jTextFieldDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButtonCadastrar)
                            .addComponent(jButtonRemover)
                            .addComponent(jButtonVoltar)))
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(11, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 674, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonCadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCadastrarActionPerformed
        try {
            if (jTextFieldIdVeiculo.getText().isEmpty()
                    || jFormattedTextFieldValor.getText().isEmpty()
                    || jTextFieldDescricao.getText().isEmpty()) {
                throw new Exception("Preencha todos os campos!");
            }
            int idVeiculo = Integer.parseInt(jTextFieldIdVeiculo.getText());
            int indiceSelecionado = jComboBoxTipoDeDespesa.getSelectedIndex();
            if (indiceSelecionado == -1) {
                throw new Exception("Selecione um Tipo de Despesa!");
            }
            int idTipoDespesa = ids[indiceSelecionado];
            double valor = Double.parseDouble(jFormattedTextFieldValor.getText());
            String descricao = jTextFieldDescricao.getText();
            String data = jFormattedTextFieldData.getText();
            movimentacaoController.registrarMovimentacao(idVeiculo, idTipoDespesa, descricao, data, valor);
            jButtonRemover.setEnabled(true);
            JOptionPane.showMessageDialog(null, "Cadastro realizado com sucesso!");
            limparCamposPreenchidos();
            atualizarTabela();
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, erro.getMessage());
        }
    }//GEN-LAST:event_jButtonCadastrarActionPerformed

    private void limparCamposPreenchidos() {
    jTextFieldIdVeiculo.setText("");
    jComboBoxTipoDeDespesa.setSelectedIndex(-1);
    jTextFieldDescricao.setText("");
    jFormattedTextFieldValor.setText("");

    jTextFieldIdVeiculo.requestFocus();
}
    private void jTextFieldIdVeiculoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldIdVeiculoActionPerformed
        jFormattedTextFieldValor.requestFocus();
    }//GEN-LAST:event_jTextFieldIdVeiculoActionPerformed

    private void jFormattedTextFieldValorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFormattedTextFieldValorActionPerformed
        jComboBoxTipoDeDespesa.requestFocus();
    }//GEN-LAST:event_jFormattedTextFieldValorActionPerformed

    private void jTextFieldDescricaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldDescricaoActionPerformed
        jFormattedTextFieldData.requestFocus();
    }//GEN-LAST:event_jTextFieldDescricaoActionPerformed

    private void jButtonRemoverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRemoverActionPerformed
        DefaultTableModel model = (DefaultTableModel) jTableMovimentacoes.getModel();
        Object[] opcoes = {"Sim", "Não"}; //Utiliza Object porque os botoes do JOptionPane podem ser obejtos de qualquer tipo
        int linha = jTableMovimentacoes.getSelectedRow();

        try {
            if (linha != -1) {
                int respostaExclusao = JOptionPane.showOptionDialog(null, "Tem certeza que deseja excluir?",
                        "Confirmar Exclusão", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, opcoes, opcoes[0]);

                if (respostaExclusao == JOptionPane.YES_OPTION) {
                    int movimentacaoDaLinha = Integer.parseInt(jTableMovimentacoes.getValueAt(linha, 0).toString()); //Retorna o id da primeira coluna transformado em String por causa do toString();
                    movimentacaoController.remover(movimentacaoDaLinha);
                    model.removeRow(linha);
                    JOptionPane.showMessageDialog(null, "Movimentação removida com sucesso!");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Selecione a linha a ser removida!");
            }
            atualizarTabela();
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, erro.getMessage());
        }
    }//GEN-LAST:event_jButtonRemoverActionPerformed

    private void jButtonVoltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonVoltarActionPerformed
        TelaPrincipal telaPrincipal = new TelaPrincipal();
        telaPrincipal.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButtonVoltarActionPerformed

    private void jButtonProcurarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonProcurarActionPerformed
        TelaBuscarVeiculoMovimentacoes popup = new TelaBuscarVeiculoMovimentacoes(jTextFieldIdVeiculo);
        popup.setVisible(true);
    }//GEN-LAST:event_jButtonProcurarActionPerformed

    private void jButtonProcurarIDDespesaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonProcurarIDDespesaActionPerformed
        TelaBuscarDespesaMovimentacoes popup = new TelaBuscarDespesaMovimentacoes();
        popup.setVisible(true);
    }//GEN-LAST:event_jButtonProcurarIDDespesaActionPerformed

    private void carregarComboTipoDespesa() {
    try {
        TipoDeDespesasDAO dao = new TipoDeDespesasDAO();
        java.util.List<TipoDeDespesa> lista = dao.listaDeTiposDeDespesas();

        jComboBoxTipoDeDespesa.removeAllItems();
        ids = new int[lista.size()]; // usa o atributo da classe

        for (int i = 0; i < lista.size(); i++) {
            TipoDeDespesa tipo = lista.get(i);
            jComboBoxTipoDeDespesa.addItem(tipo.getDescricao());
            ids[i] = tipo.getIdTipoDeDespesa(); // salva no atributo
        }
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Erro ao carregar tipos de despesa: " + e.getMessage());
    }
}


    private void jComboBoxTipoDeDespesaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxTipoDeDespesaActionPerformed

    }//GEN-LAST:event_jComboBoxTipoDeDespesaActionPerformed

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
    } catch (ClassNotFoundException ex) {
        java.util.logging.Logger.getLogger(TelaMovimentacao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    } catch (InstantiationException ex) {
        java.util.logging.Logger.getLogger(TelaMovimentacao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    } catch (IllegalAccessException ex) {
        java.util.logging.Logger.getLogger(TelaMovimentacao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    } catch (javax.swing.UnsupportedLookAndFeelException ex) {
        java.util.logging.Logger.getLogger(TelaMovimentacao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    }
    //</editor-fold>
    //</editor-fold>

    java.awt.EventQueue.invokeLater(new Runnable() {
        public void run() {
            new TelaMovimentacao().setVisible(true);
        }
    });
}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButtonCadastrar;
    private javax.swing.JButton jButtonProcurar;
    private javax.swing.JButton jButtonProcurarIDDespesa;
    private javax.swing.JButton jButtonRemover;
    private javax.swing.JButton jButtonVoltar;
    private javax.swing.JComboBox<String> jComboBoxTipoDeDespesa;
    private javax.swing.JFormattedTextField jFormattedTextFieldData;
    private javax.swing.JFormattedTextField jFormattedTextFieldValor;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableMovimentacoes;
    private javax.swing.JTextField jTextFieldDescricao;
    private javax.swing.JTextField jTextFieldIdVeiculo;
    // End of variables declaration//GEN-END:variables
}
