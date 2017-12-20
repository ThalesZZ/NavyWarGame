package interfaces;

import java.awt.Color;
import javax.swing.JPanel;
import new_objetos.*;

public class FrmJogo extends javax.swing.JFrame {

    private char vez; // j jogador c computador
    private int dicas;
    private char tiroSelecionado;

    private int turno;

    private Celula mapaComputador[][] = new Celula[10][10];
    private Celula mapaJogador[][] = new Celula[10][10];

    private Campo campoComputador;
    private Campo campoJogador;

    private boolean gg;

    public FrmJogo(Jogo jogo) {
        initComponents();

        vez = 'j';
        dicas = 3;
        tiroSelecionado = 's';
        turno = 1;
        gg = false;

        this.campoComputador = jogo.getCampoComputador();
        this.campoJogador = jogo.getCampoJogador();

        mapaComputador = Utilidade.loadMap(campoComputador, this, true, true);
        Utilidade.construirMapa(mapaComputador, PanelCampoComputador);
        mapaJogador = Utilidade.loadMap(campoJogador, this, false, true);
        Utilidade.construirMapa(mapaJogador, PanelCampoJogador);

    }

    public void setMessage(String message, Color color) {
        lblMsg.setText(message);
        lblMsg.setForeground(color);
    }

    private void jogadaComputador() {
        setTurno(turno + 1);

        int linhaSorteada, colunaSorteada;

        do {
            linhaSorteada = Utilidade.random(0, 9);
            colunaSorteada = Utilidade.random(0, 9);
        } while (mapaJogador[linhaSorteada][colunaSorteada].getParte().isAtingida());

        if (campoComputador.getSubmarino().isVivo()
                || campoComputador.getCaca().isVivo()
                || campoComputador.getNavio().isVivo()) {

            int d = 0;

            do {
                d = Utilidade.random(1, 3);

                switch (d) {
                    case 1:
                        if (!campoComputador.getSubmarino().isVivo()) {
                            d = 0;
                        }
                        break;
                    case 2:
                        if (!campoComputador.getNavio().isVivo()) {
                            d = 0;
                        }
                        break;
                    case 3:
                        if (!campoComputador.getCaca().isVivo()) {
                            d = 0;
                        }
                        break;
                }

            } while (d == 0);

            switch (d) {
                case 1:
                    campoComputador.getSubmarino().disparo(mapaJogador[linhaSorteada][colunaSorteada], this, false);
                    break;
                case 2:
                    campoComputador.getNavio().disparo(mapaJogador[linhaSorteada][colunaSorteada], this, false);
                    break;
                case 3:
                    campoComputador.getCaca().disparo(mapaJogador[linhaSorteada][colunaSorteada], this, false);
                    break;
            }

            if (!(campoJogador.getSubmarino().isVivo()
                    || campoJogador.getCaca().isVivo()
                    || campoJogador.getNavio().isVivo())) {

                setMessage("OH NÃO... VAMOS FAZER MELHOR NA PRÓXIMA.", new Color(220, 0, 0));

                for (int i = 0; i < 10; i++) {
                    for (int j = 0; j < 10; j++) {
                        campoComputador.getSubmarino().disparo(mapaJogador[i][j], this, true);
                    }
                }
                
                gg = true;
                
                Utilidade.construirMapa(mapaComputador, PanelCampoComputador);
                Utilidade.construirMapa(mapaJogador, PanelCampoJogador);

            } else {

                if (!campoJogador.getSubmarino().isVivo()) {
                    lblSubmarinoAliado.setForeground(new Color(100, 100, 100));
                    //setMessage("NOSSO SUBMARINO FOI ABATIDO!", new Color(204,0,0));
                }

                if (!campoJogador.getNavio().isVivo()) {
                    lblNavioAliado.setForeground(new Color(100, 100, 100));
                    //setMessage("NAVIO DE ESCOLTA DESTRUÍDO!", new Color(204,0,0));
                }

                if (!campoJogador.getCaca().isVivo()) {
                    lblCacaAliado.setForeground(new Color(100, 100, 100));
                    //setMessage("O CAÇA PERDEU O CONTROLE E CAIU NO MAR!", new Color(204,0,0));
                }

                if (!campoJogador.getPortaAviao().isVivo()) {
                    lblPortaAliado.setForeground(new Color(100, 100, 100));
                    //setMessage("PORTA AVIÕES ANIQUILADO!.", new Color(204,0,0));
                }

                if ((!campoJogador.getSubmarino().isVivo() && tiroSelecionado == 's')
                        || (!campoJogador.getNavio().isVivo() && tiroSelecionado == 'n')
                        || (!campoJogador.getCaca().isVivo() && tiroSelecionado == 'c')) {
                    btnTrocarDisparoActionPerformed(null);
                    setMessage("ATIRADOR SUBSTITUÍDO!", new Color(255, 255, 153));
                }
            }

            Utilidade.construirMapa(mapaJogador, PanelCampoJogador);
            Utilidade.construirMapa(mapaComputador, PanelCampoComputador);

        } else {
            setMessage("MUITO BEM! VENCEMOS A BATALHA!", new Color(0, 153, 255));

            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 10; j++) {
                    campoJogador.getSubmarino().disparo(mapaComputador[i][j], this, true);
                }
            }

            gg = true;

            Utilidade.construirMapa(mapaJogador, PanelCampoJogador);
            Utilidade.construirMapa(mapaComputador, PanelCampoComputador);
        }

    }

    public void attVeiculosVivos() {
        if (!(campoComputador.getSubmarino().isVivo())) {
            lblSubmarinoInimigo.setForeground(new Color(100, 100, 100));
            //setMessage("ABATEMOS O SUBMARINO INIMIGO!", new Color(51,153,255));
        }

        if (!(campoComputador.getNavio().isVivo())) {
            lblNavioInimigo.setForeground(new Color(100, 100, 100));
            //setMessage("NAVIO INIMIGO DESTRUÍDO!", new Color(51,153,255));
        }

        if (!(campoComputador.getCaca().isVivo())) {
            lblCacaInimigo.setForeground(new Color(100, 100, 100));
            //setMessage("EXPLODIMOS O CAÇA INIMIGO!", new Color(51,153,255));
        }

        if (!(campoComputador.getPortaAviao().isVivo())) {
            lblPortaInimigo.setForeground(new Color(100, 100, 100));
            //setMessage("PORTA AVIÃO INIMIGO ANIQUILADO!", new Color(51,153,255));
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        PanelAll = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        lblMsg = new javax.swing.JLabel();
        lblMsg1 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        lblImageDisparo = new javax.swing.JLabel();
        btnTrocarDisparo = new javax.swing.JButton();
        jPanel9 = new javax.swing.JPanel();
        lblPortaInimigo = new javax.swing.JLabel();
        lblNavioInimigo = new javax.swing.JLabel();
        lblSubmarinoInimigo = new javax.swing.JLabel();
        lblCacaInimigo = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        lblPortaAliado = new javax.swing.JLabel();
        lblNavioAliado = new javax.swing.JLabel();
        lblSubmarinoAliado = new javax.swing.JLabel();
        lblCacaAliado = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        lblMsg2 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        PanelCampoComputador = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        PanelCampoJogador = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        lblMsg10 = new javax.swing.JLabel();
        cmbRef = new javax.swing.JComboBox<>();
        cmbNum = new javax.swing.JComboBox<>();
        btnDica = new javax.swing.JButton();
        lblDetectado = new javax.swing.JLabel();
        jPanel15 = new javax.swing.JPanel();
        lblMsg9 = new javax.swing.JLabel();
        lblTurno = new javax.swing.JLabel();
        jPanel19 = new javax.swing.JPanel();
        btnSair = new javax.swing.JButton();
        btnReiniciar = new javax.swing.JButton();
        btnNovoJogo = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        PanelAll.setBackground(new java.awt.Color(30, 30, 30));
        PanelAll.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 3, 3, 3, new java.awt.Color(255, 51, 0)));

        jLabel21.setFont(new java.awt.Font("Century Gothic", 0, 24)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(30, 30, 30));
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel21.setText("CAMPO INIMIGO");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel21)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel21)
                .addContainerGap())
        );

        jLabel22.setFont(new java.awt.Font("Century Gothic", 0, 24)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(30, 30, 30));
        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel22.setText("CAMPO ALIADO");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel22)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel22)
                .addContainerGap())
        );

        jPanel1.setBackground(new java.awt.Color(50, 50, 50));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 240, 240), 2));

        lblMsg.setFont(new java.awt.Font("Century Gothic", 1, 20)); // NOI18N
        lblMsg.setForeground(new java.awt.Color(255, 255, 153));
        lblMsg.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblMsg.setText("DISPARE!");

        lblMsg1.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        lblMsg1.setForeground(new java.awt.Color(255, 50, 50));
        lblMsg1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblMsg1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/radio.png"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(lblMsg1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblMsg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblMsg1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lblMsg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel5.setBackground(new java.awt.Color(30, 30, 30));

        jLabel23.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(240, 240, 240));
        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel23.setText("DISPARO");

        lblImageDisparo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblImageDisparo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/DisparoSubmarinoOn.jpg"))); // NOI18N
        lblImageDisparo.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel23, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblImageDisparo)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel23)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblImageDisparo)
                .addContainerGap())
        );

        btnTrocarDisparo.setBackground(new java.awt.Color(32, 120, 214));
        btnTrocarDisparo.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        btnTrocarDisparo.setForeground(new java.awt.Color(240, 240, 240));
        btnTrocarDisparo.setText("TROCAR");
        btnTrocarDisparo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(30, 30, 30), 2));
        btnTrocarDisparo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnTrocarDisparo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTrocarDisparoActionPerformed(evt);
            }
        });

        jPanel9.setBackground(new java.awt.Color(30, 30, 30));

        lblPortaInimigo.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        lblPortaInimigo.setForeground(new java.awt.Color(204, 0, 0));
        lblPortaInimigo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPortaInimigo.setText("PORTA AVIÃO");

        lblNavioInimigo.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        lblNavioInimigo.setForeground(new java.awt.Color(204, 0, 0));
        lblNavioInimigo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNavioInimigo.setText("NAV. ESCOLTA");

        lblSubmarinoInimigo.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        lblSubmarinoInimigo.setForeground(new java.awt.Color(204, 0, 0));
        lblSubmarinoInimigo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSubmarinoInimigo.setText("SUBMARINO");

        lblCacaInimigo.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        lblCacaInimigo.setForeground(new java.awt.Color(204, 0, 0));
        lblCacaInimigo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCacaInimigo.setText("CAÇA");

        jLabel24.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(240, 240, 240));
        jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel24.setText("INIMIGO");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblPortaInimigo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblNavioInimigo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblSubmarinoInimigo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblCacaInimigo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel24, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel24)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblPortaInimigo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblNavioInimigo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblSubmarinoInimigo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblCacaInimigo)
                .addContainerGap())
        );

        jPanel10.setBackground(new java.awt.Color(30, 30, 30));

        lblPortaAliado.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        lblPortaAliado.setForeground(new java.awt.Color(51, 153, 255));
        lblPortaAliado.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPortaAliado.setText("PORTA AVIÃO");

        lblNavioAliado.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        lblNavioAliado.setForeground(new java.awt.Color(51, 153, 255));
        lblNavioAliado.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNavioAliado.setText("NAV. ESCOLTA");

        lblSubmarinoAliado.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        lblSubmarinoAliado.setForeground(new java.awt.Color(51, 153, 255));
        lblSubmarinoAliado.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSubmarinoAliado.setText("SUBMARINO");

        lblCacaAliado.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        lblCacaAliado.setForeground(new java.awt.Color(51, 153, 255));
        lblCacaAliado.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCacaAliado.setText("CAÇA");

        jLabel25.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(240, 240, 240));
        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel25.setText("ALIADO");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblPortaAliado, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblNavioAliado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblSubmarinoAliado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblCacaAliado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel25, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel25)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblPortaAliado)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblNavioAliado)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblSubmarinoAliado)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblCacaAliado)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnTrocarDisparo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(btnTrocarDisparo)
                .addGap(39, 39, 39)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        lblMsg2.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        lblMsg2.setForeground(new java.awt.Color(30, 30, 30));
        lblMsg2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblMsg2.setText("TORRE DE CONTROLE");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblMsg2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(lblMsg2))
        );

        jPanel7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 240, 240), 2));

        PanelCampoComputador.setBackground(new java.awt.Color(204, 204, 204));
        PanelCampoComputador.setLayout(new java.awt.GridLayout(10, 10));

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PanelCampoComputador, javax.swing.GroupLayout.PREFERRED_SIZE, 550, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PanelCampoComputador, javax.swing.GroupLayout.PREFERRED_SIZE, 550, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jPanel8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 240, 240), 2));

        PanelCampoJogador.setBackground(new java.awt.Color(204, 204, 204));
        PanelCampoJogador.setLayout(new java.awt.GridLayout(10, 10));

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PanelCampoJogador, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 550, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PanelCampoJogador, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel14.setBackground(new java.awt.Color(50, 50, 50));
        jPanel14.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 240, 240), 2));

        lblMsg10.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        lblMsg10.setForeground(new java.awt.Color(255, 50, 50));
        lblMsg10.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblMsg10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/radar.png"))); // NOI18N

        cmbRef.setBackground(new java.awt.Color(240, 240, 240));
        cmbRef.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        cmbRef.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "LINHA", "COLUNA" }));

        cmbNum.setBackground(new java.awt.Color(240, 240, 240));
        cmbNum.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        cmbNum.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J" }));
        cmbNum.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                cmbNumMouseMoved(evt);
            }
        });

        btnDica.setBackground(new java.awt.Color(239, 239, 239));
        btnDica.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        btnDica.setForeground(new java.awt.Color(30, 30, 30));
        btnDica.setText("RASTREAR (3)");
        btnDica.setBorder(null);
        btnDica.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnDica.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDicaActionPerformed(evt);
            }
        });

        lblDetectado.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        lblDetectado.setForeground(new java.awt.Color(240, 240, 240));
        lblDetectado.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addComponent(lblMsg10, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(cmbRef, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(cmbNum, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblDetectado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(btnDica, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblMsg10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(cmbRef)
            .addComponent(cmbNum)
            .addComponent(btnDica, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lblDetectado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        lblMsg9.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        lblMsg9.setForeground(new java.awt.Color(30, 30, 30));
        lblMsg9.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblMsg9.setText("RASTREAMENTO");

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblMsg9)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(lblMsg9))
        );

        lblTurno.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        lblTurno.setForeground(new java.awt.Color(240, 240, 240));
        lblTurno.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTurno.setText("TURNO 1");

        javax.swing.GroupLayout PanelAllLayout = new javax.swing.GroupLayout(PanelAll);
        PanelAll.setLayout(PanelAllLayout);
        PanelAllLayout.setHorizontalGroup(
            PanelAllLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelAllLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(PanelAllLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelAllLayout.createSequentialGroup()
                        .addGroup(PanelAllLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(PanelAllLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblTurno, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(PanelAllLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        PanelAllLayout.setVerticalGroup(
            PanelAllLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelAllLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(PanelAllLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(PanelAllLayout.createSequentialGroup()
                        .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(PanelAllLayout.createSequentialGroup()
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addGroup(PanelAllLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblTurno, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(18, 18, 18)
                .addGroup(PanelAllLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(PanelAllLayout.createSequentialGroup()
                        .addGroup(PanelAllLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, 0)
                        .addGroup(PanelAllLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel19.setBackground(new java.awt.Color(51, 51, 51));

        btnSair.setBackground(new java.awt.Color(255, 0, 51));
        btnSair.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        btnSair.setForeground(new java.awt.Color(240, 240, 240));
        btnSair.setText("SAIR");
        btnSair.setBorder(null);
        btnSair.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSairActionPerformed(evt);
            }
        });

        btnReiniciar.setBackground(new java.awt.Color(153, 102, 255));
        btnReiniciar.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        btnReiniciar.setForeground(new java.awt.Color(240, 240, 240));
        btnReiniciar.setText("REINICIAR");
        btnReiniciar.setBorder(null);
        btnReiniciar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnReiniciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReiniciarActionPerformed(evt);
            }
        });

        btnNovoJogo.setBackground(new java.awt.Color(51, 153, 255));
        btnNovoJogo.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        btnNovoJogo.setForeground(new java.awt.Color(240, 240, 240));
        btnNovoJogo.setText("NOVO JOGO");
        btnNovoJogo.setBorder(null);
        btnNovoJogo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnNovoJogo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNovoJogoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel19Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnNovoJogo, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(btnReiniciar, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(btnSair, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnSair, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
            .addComponent(btnNovoJogo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnReiniciar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PanelAll, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(PanelAll, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnTrocarDisparoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTrocarDisparoActionPerformed
        switch (tiroSelecionado) {
            case 's':
                if (campoJogador.getNavio().isVivo()) {
                    tiroSelecionado = 'n';
                } else if (campoJogador.getCaca().isVivo()) {
                    tiroSelecionado = 'c';
                }
                break;
            case 'n':
                if (campoJogador.getCaca().isVivo()) {
                    tiroSelecionado = 'c';
                } else if (campoJogador.getSubmarino().isVivo()) {
                    tiroSelecionado = 's';
                }
                break;
            case 'c':
                if (campoJogador.getSubmarino().isVivo()) {
                    tiroSelecionado = 's';
                } else if (campoJogador.getNavio().isVivo()) {
                    tiroSelecionado = 'n';
                }
                break;
        }

        switch (tiroSelecionado) {
            case 's':
                lblImageDisparo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/DisparoSubmarinoOn.jpg")));
                break;
            case 'n':
                lblImageDisparo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/DisparoNavioOn.jpg")));
                break;
            case 'c':
                lblImageDisparo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/DisparoCacaOn.jpg")));
                break;
        }

    }//GEN-LAST:event_btnTrocarDisparoActionPerformed

    private void btnDicaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDicaActionPerformed
        boolean achou = false;
        dicas--;
        int n = cmbNum.getSelectedIndex();

        if (cmbRef.getSelectedIndex() == 0) { //linha            
            for (int i = 0; i < 10; i++) {
                if (mapaComputador[n][i].getParte().getVeiculo() != 'a') {
                    achou = true;
                    break;
                }
            }
        } else { //coluna
            for (int i = 0; i < 10; i++) {
                if (mapaComputador[i][n].getParte().getVeiculo() != 'a') {
                    achou = true;
                    break;
                }
            }
        }

        if (achou) {
            lblDetectado.setText("DETECTADO!");
            lblDetectado.setForeground(new Color(0, 200, 0));
        } else {
            lblDetectado.setText("NADA...");
            lblDetectado.setForeground(new Color(240, 240, 240));
        }

        btnDica.setText("RASTREAR(" + dicas + ")");

        if (dicas <= 0) {
            btnDica.setEnabled(false);
        }

    }//GEN-LAST:event_btnDicaActionPerformed

    private void btnSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSairActionPerformed
        new TelaInicial().setVisible(true);
        dispose();
    }//GEN-LAST:event_btnSairActionPerformed

    private void btnReiniciarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReiniciarActionPerformed
        dispose();

        FrmMontarJogo frame = new FrmMontarJogo(campoJogador, '-');
        frame.setVisible(true);
        frame.resetGame();
        frame.dispose();
    }//GEN-LAST:event_btnReiniciarActionPerformed

    private void btnNovoJogoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovoJogoActionPerformed
        new FrmMontarJogo(null, 'p').setVisible(true);
        dispose();
    }//GEN-LAST:event_btnNovoJogoActionPerformed

    private void cmbNumMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbNumMouseMoved
        int ind = cmbNum.getSelectedIndex();
        cmbNum.removeAllItems();

        if (cmbRef.getSelectedIndex() == 0) {
            cmbNum.addItem("A");
            cmbNum.addItem("B");
            cmbNum.addItem("C");
            cmbNum.addItem("D");
            cmbNum.addItem("E");
            cmbNum.addItem("F");
            cmbNum.addItem("G");
            cmbNum.addItem("H");
            cmbNum.addItem("I");
            cmbNum.addItem("J");
        } else {
            for (int i = 1; i <= 10; i++) {
                cmbNum.addItem("" + i);
            }
        }

        cmbNum.setSelectedItem(ind);
    }//GEN-LAST:event_cmbNumMouseMoved

    public boolean isGg() {
        return gg;
    }

    public void setGg(boolean gg) {
        this.gg = gg;
    }

    public int getDicas() {
        return dicas;
    }

    public void setDicas(int dicas) {
        this.dicas = dicas;
    }

    public int getTurno() {
        return turno;
    }

    public void setTurno(int turno) {
        this.turno = turno;
        lblTurno.setText("TURNO " + this.turno);
    }

    public char getVez() {
        return vez;
    }

    public void setVez(char vez) {
        this.vez = vez;
        jogadaComputador();
    }

    public char getTiroSelecionado() {
        return tiroSelecionado;
    }

    public void setTiroSelecionado(char tiroSelecionado) {
        this.tiroSelecionado = tiroSelecionado;
    }

    public Celula[][] getMapaComputador() {
        return mapaComputador;
    }

    public void setMapaComputador(Celula[][] mapaComputador) {
        this.mapaComputador = mapaComputador;
    }

    public void setCelulaMapaComputador(Celula c) {
        mapaComputador[c.getLinha()][c.getColuna()] = c;
    }

    public Celula[][] getMapaJogador() {
        return mapaJogador;
    }

    public void setMapaJogador(Celula[][] mapaJogador) {
        this.mapaJogador = mapaJogador;
    }

    public void setCelulaMapaJogador(Celula c) {
        mapaJogador[c.getLinha()][c.getColuna()] = c;
    }

    public Campo getCampoComputador() {
        return campoComputador;
    }

    public void setCampoComputador(Campo campoComputador) {
        this.campoComputador = campoComputador;
    }

    public Campo getCampoJogador() {
        return campoJogador;
    }

    public void setCampoJogador(Campo campoJogador) {
        this.campoJogador = campoJogador;
    }

    public JPanel getPanelCampoComputador() {
        return PanelCampoComputador;
    }

    public void setPanelCampoComputador(JPanel PanelCampoComputador) {
        this.PanelCampoComputador = PanelCampoComputador;
    }

    public JPanel getPanelCampoJogador() {
        return PanelCampoJogador;
    }

    public void setPanelCampoJogador(JPanel PanelCampoJogador) {
        this.PanelCampoJogador = PanelCampoJogador;
    }

    public static void main(String args[]) {

        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Metal".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FrmJogo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmJogo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmJogo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmJogo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmJogo(new Jogo(Utilidade.gerarCampoAleatorio(), Utilidade.gerarCampoAleatorio())).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PanelAll;
    private javax.swing.JPanel PanelCampoComputador;
    private javax.swing.JPanel PanelCampoJogador;
    private javax.swing.JButton btnDica;
    private javax.swing.JButton btnNovoJogo;
    private javax.swing.JButton btnReiniciar;
    private javax.swing.JButton btnSair;
    private javax.swing.JButton btnTrocarDisparo;
    private javax.swing.JComboBox<String> cmbNum;
    private javax.swing.JComboBox<String> cmbRef;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JLabel lblCacaAliado;
    private javax.swing.JLabel lblCacaInimigo;
    private javax.swing.JLabel lblDetectado;
    private javax.swing.JLabel lblImageDisparo;
    private javax.swing.JLabel lblMsg;
    private javax.swing.JLabel lblMsg1;
    private javax.swing.JLabel lblMsg10;
    private javax.swing.JLabel lblMsg2;
    private javax.swing.JLabel lblMsg9;
    private javax.swing.JLabel lblNavioAliado;
    private javax.swing.JLabel lblNavioInimigo;
    private javax.swing.JLabel lblPortaAliado;
    private javax.swing.JLabel lblPortaInimigo;
    private javax.swing.JLabel lblSubmarinoAliado;
    private javax.swing.JLabel lblSubmarinoInimigo;
    private javax.swing.JLabel lblTurno;
    // End of variables declaration//GEN-END:variables
}
