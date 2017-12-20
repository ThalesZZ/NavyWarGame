package interfaces;

import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import new_objetos.*;

public class Celula extends javax.swing.JPanel {

    private FrmMontarJogo frameMontar;
    private FrmJogo frame;
    private Campo campo;
    private Veiculo veiculo;
    private Parte parte;
    private final boolean inGame;
    private final int linha;
    private final int coluna;
    private final boolean inimigo;

    private void inGame() {
        if (inimigo && !frame.isGg()) {
            
                addMouseListener(new MouseAdapter() {

                    @Override
                    public void mouseClicked(MouseEvent e) {
                        if (!parte.isAtingida()) {

                            switch (frame.getTiroSelecionado()) {
                                case 's':
                                    campo.getSubmarino().disparo(Celula.this, frame, inimigo);
                                    break;
                                case 'n':
                                    campo.getNavio().disparo(Celula.this, frame, inimigo);
                                    break;
                                case 'c':
                                    campo.getCaca().disparo(Celula.this, frame, inimigo);
                                    break;
                            }

                            frame.setCelulaMapaComputador(Celula.this);
                            Utilidade.construirMapa(frame.getMapaComputador(), frame.getPanelCampoComputador());

                            if (frame.getVez() == 'j') {
                                frame.setVez('c');
                            } else {
                                frame.setVez('j');
                            }

                            frame.attVeiculosVivos();
                        }
                    }

                });

                addMouseMotionListener(new MouseMotionAdapter() {
                    @Override
                    public void mouseMoved(MouseEvent e) {
                        if (!parte.isAtingida()) {
                            lblImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/aguaSelecting.jpg")));
                        }
                    }
                });
                addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseExited(MouseEvent e) {
                        if (!parte.isAtingida()) {
                            lblImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/agua.jpg")));
                        }
                    }
                });
        } else {

            if (veiculo == null) {
                lblImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/agua" + (parte.isAtingida() ? "Atingida" : "") + ".jpg")));
            } else if (veiculo instanceof PortaAviao) {
                lblImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/PortaAviao" + (parte.isAtingida() ? "Atingido" : "") + parte.getIndex() + ".jpg")));
            } else if (veiculo instanceof Navio) {
                lblImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/NavioDeEscolta" + (parte.isAtingida() ? "Atingido" : "") + parte.getIndex() + ".jpg")));
            } else if (veiculo instanceof Submarino) {
                lblImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Submarino" + (parte.isAtingida() ? "Atingido" : "") + parte.getIndex() + ".jpg")));
            } else if (veiculo instanceof Caca) {
                lblImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Caca" + (parte.isAtingida() ? "Atingido" : "") + parte.getIndex() + ".jpg")));
            }

        }
    }

    public void atingirParte(Celula c) {
        c.setParte(new Parte(true, c.getParte().getIndex(), c.getParte().getLinha(), c.getParte().getLinha(), c.getParte().getVeiculo()));

        if (c.getVeiculo() != null) {
            c.getVeiculo().setParte(c.getParte(), c.getParte().getIndex());
        }

        switch (c.getParte().getVeiculo()) {
            case 'a':
                c.setLblImage("aguaAtingida.jpg");
                break;
            case 'p':
                c.setLblImage("PortaAviaoAtingido" + parte.getIndex() + ".jpg");
                break;
            case 'n':
                c.setLblImage("NavioDeEscoltaAtingido" + parte.getIndex() + ".jpg");
                break;
            case 's':
                c.setLblImage("SubmarinoAtingido" + parte.getIndex() + ".jpg");
                break;
            case 'c':
                c.setLblImage("CacaAtingido" + parte.getIndex() + ".jpg");
                break;
        }

        setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }

    private void notInGame() {
        switch (parte.getVeiculo()) {
            case 'p':
                lblImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/PortaAviao" + parte.getIndex() + ".jpg")));
                break;
            case 'n':
                lblImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/NavioDeEscolta" + parte.getIndex() + ".jpg")));
                break;
            case 's':
                lblImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Submarino" + parte.getIndex() + ".jpg")));
                break;
            case 'c':
                lblImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Caca" + parte.getIndex() + ".jpg")));
                break;
            case 'a':
                lblImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/agua.jpg")));
                break;
        }

        /*addMouseListener(new MouseAdapter() {

                @Override
                public void mouseClicked(MouseEvent e) {
                    if(!selected && validarPosicao()){
                        char v = frameMontar.getVeicSel();
                        Parte partes[];
                        
                        switch(v){
                            case 'p':
                                partes = new Parte[4];
                                
                                for(int i = 0; i < 4; i++)
                                    partes[i] = new Parte(false, i, linha, coluna + i, 'p');     
                                
                                //campo.setPortaAviao(new PortaAviao(linha, coluna, partes));
                                
                                for(int i = 0; i < 4; i++)
                                    frameMontar.setCelulaMapa(new Celula(campo, partes[i], inGame, frameMontar, false, true));
                                break;
                            
                        }
                        
                        Utilidade.construirMapa(frameMontar.getMapa(), frameMontar.getPanelCampo());
                    }
                }

            });
/*
            addMouseMotionListener(new MouseMotionAdapter() {
                @Override
                public void mouseMoved(MouseEvent e) {
                    if (!parte.isAtingida()) {
                        lblImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/aguaSelecting.jpg")));
                    }
                }
            });
            addMouseListener(new MouseAdapter() {
                @Override
                public void mouseExited(MouseEvent e) {
                    if (!parte.isAtingida()) {
                        lblImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/agua.jpg")));
                    }
                }
            });*/
    }

    public Celula(Campo campo, Parte parte, boolean inGame, JFrame frame, boolean inimigo) {
        initComponents();

        if (frame instanceof FrmJogo) {
            this.frame = (FrmJogo) frame;
        } else if (frame instanceof FrmMontarJogo) {
            this.frameMontar = (FrmMontarJogo) frame;
        }

        this.campo = campo;

        this.parte = parte;

        this.linha = parte.getLinha();
        this.coluna = parte.getColuna();

        this.inimigo = inimigo;

        this.inGame = inGame;

        switch (parte.getVeiculo()) {
            case 'a':
                this.veiculo = null;
                break;
            case 'p':
                this.veiculo = campo.getPortaAviao();
                break;
            case 'n':
                this.veiculo = campo.getNavio();
                break;
            case 's':
                this.veiculo = campo.getSubmarino();
                break;
            case 'c':
                this.veiculo = campo.getCaca();
                break;
        }

        setCursor(new Cursor((parte.isAtingida() || !inGame || !inimigo ? Cursor.DEFAULT_CURSOR : Cursor.HAND_CURSOR)));

        if (inGame) {
            inGame();
        } else {
            notInGame();
        }

    }

    public int getLinha() {
        return linha;
    }

    public int getColuna() {
        return coluna;
    }

    public FrmJogo getFrame() {
        return frame;
    }

    public void setFrame(FrmJogo frame) {
        this.frame = frame;
    }

    public Campo getCampo() {
        return campo;
    }

    public void setCampo(Campo campo) {
        this.campo = campo;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    public Parte getParte() {
        return parte;
    }

    public void setParte(Parte parte) {
        this.parte = parte;
    }

    public JPanel getPanel() {
        return panel;
    }

    public void setPanel(JPanel panel) {
        this.panel = panel;
    }

    public boolean isInimigo() {
        return inimigo;
    }

    public JLabel getLblImage() {
        return lblImage;
    }

    public void setLblImage(String nameImg) {
        this.lblImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/" + nameImg)));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        panel = new javax.swing.JPanel();
        lblImage = new javax.swing.JLabel();

        jLabel6.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Things:");

        jLabel7.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Hello Doctor");

        setBackground(new java.awt.Color(255, 255, 255));
        setMaximumSize(new java.awt.Dimension(55, 55));
        setMinimumSize(new java.awt.Dimension(55, 55));
        setPreferredSize(new java.awt.Dimension(55, 55));
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                formMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                formMouseExited(evt);
            }
        });

        lblImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/agua.jpg"))); // NOI18N

        javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(lblImage))
        );
        panelLayout.setVerticalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(lblImage, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void formMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseEntered

    }//GEN-LAST:event_formMouseEntered

    private void formMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseExited

    }//GEN-LAST:event_formMouseExited


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel lblImage;
    private javax.swing.JPanel panel;
    // End of variables declaration//GEN-END:variables

}
