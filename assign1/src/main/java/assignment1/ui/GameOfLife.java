package assignment1.ui;

import assignment1.GameBoard;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import java.util.Collections;

public class GameOfLife extends javax.swing.JFrame {
    private JButton jButtonGenerateBoard;
    private JLabel jLabelBoardSize;
    private JPanel jPanelBoard;
    private JTextField jTextFieldBoardSize;
    private JButton jButtonRunGame;
    private Cell[][] jButtonCells;
    private JLabel jLabelInstrucntionDeadToAlive;
    private JLabel jLabelInstrucntionAliveToDead;

    boolean[][] cells;
    final private static boolean ALIVE = true;
    final private static boolean DEAD = false;
    
    final private static String RUN_GAME = "Run Game";
    final private static String STOP_GAME = "Stop Game";
    final private static String ALIVE_SYMBOL = "X";
    final private static String DEAD_SYMBOL = " ";
    final private static int CELL_SIZE = 50;
    
    public GameOfLife() {
        initGUI();
    }

    private void initFormComponents()
    {
        jPanelBoard = new JPanel();
        jTextFieldBoardSize = new JTextField();
        jLabelBoardSize = new JLabel();
        jButtonGenerateBoard = new JButton();
        jButtonRunGame = new JButton();
        jLabelInstrucntionDeadToAlive = new JLabel("<html><i>*Click on a dead cell to turn it alive.</i></html>");
        jLabelInstrucntionAliveToDead = new JLabel("<html><i>*Click on an alive cell to turn it dead.</i></html>");
        
        jButtonRunGame.setText(RUN_GAME);
        jLabelBoardSize.setText("Board Size:");
        jButtonGenerateBoard.setText("Generate Board");
        jLabelBoardSize.getAccessibleContext().setAccessibleName("jLabelBoardSize");
        
        jButtonRunGame.setEnabled(false);
        
        registerActionListenerForGenerateBoardButton();
        registerActionListnerForRunGameButton();
    }
    
    private void initForm()
    {
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Game of Life");
    }
    
    private void initLayoutForBoardPanel()
    {
        javax.swing.GroupLayout jPanelBoardLayout = new javax.swing.GroupLayout(jPanelBoard);
        jPanelBoard.setLayout(jPanelBoardLayout);
        jPanelBoardLayout.setHorizontalGroup(
            jPanelBoardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 361, Short.MAX_VALUE)
        );
        jPanelBoardLayout.setVerticalGroup(
            jPanelBoardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 182, Short.MAX_VALUE)
        );
    }
    
    private void initContentLayout()
    {
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabelBoardSize)
                        .addGap(18, 18, 18)
                        .addComponent(jTextFieldBoardSize, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButtonGenerateBoard)
                    .addComponent(jButtonRunGame)
                    .addComponent(jLabelInstrucntionDeadToAlive)
                    .addComponent(jLabelInstrucntionAliveToDead)
                    .addComponent(jPanelBoard, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(27, Short.MAX_VALUE))
        );
        
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldBoardSize, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelBoardSize))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonGenerateBoard)
                .addComponent(jButtonRunGame)
                .addComponent(jLabelInstrucntionDeadToAlive)
                .addComponent(jLabelInstrucntionAliveToDead)
                .addGap(18, 18, 18)
                .addComponent(jPanelBoard, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(33, Short.MAX_VALUE))
        );
    }

    private void handleGenerateBoardButtonClick(ActionEvent event) {
       setupInitialBoardPanel();
       jButtonRunGame.setEnabled(true);
    }
    
    private void registerActionListenerForGenerateBoardButton()
    {   
        jButtonGenerateBoard.addActionListener(this::handleGenerateBoardButtonClick);
    }
    
    private void handleRunGameButtonClick(ActionEvent event) 
    {
        toggleTextOnRunGameButton();
        toggleEnabilityGenerateBoardButton();
        toggleAllCellButton();
        runAnimation();        
    }
    
    private void registerActionListnerForRunGameButton()
    {
        jButtonRunGame.addActionListener(this::handleRunGameButtonClick);
    }
    
    private void toggleEnabilityGenerateBoardButton()
    {
        jButtonGenerateBoard.setEnabled(!jButtonGenerateBoard.isEnabled());
    }
    
    private void toggleAllCellButton()
    {
        for(int i = 0; i < jButtonCells.length; i++)
        {
            for(int j = 0; j < jButtonCells[0].length; j++)
            {      
                jButtonCells[i][j].setEnabled(!jButtonCells[i][j].isEnabled());
            }
        }
    }
    
    private void registerActionListenerForCellButton(JButton button)
    {
        button.addActionListener(event -> markCell((JButton)event.getSource(), (button.getText().equals(DEAD_SYMBOL) ? true : false)));
    }

    private void toggleTextOnRunGameButton()
    {
        if(jButtonRunGame.getText() == RUN_GAME)
        {
            jButtonRunGame.setText(STOP_GAME);
        }else
        {
            jButtonRunGame.setText(RUN_GAME);
        }
    }

    private void setupInitialBoardPanel()
    {
        int boardSize = Integer.parseInt(jTextFieldBoardSize.getText());
        cells = new boolean[boardSize][boardSize];
        jButtonCells = new Cell[boardSize][boardSize];
        
        GridLayout grl = new GridLayout(boardSize,boardSize);
        grl.setHgap(0);
        grl.setVgap(0);
        jPanelBoard.setLayout(grl);
        
        jPanelBoard.removeAll();
    
        for(int i = 0; i < boardSize; i++)
        {
            for(int j = 0; j < boardSize; j++)
            {   
                Cell btn = new Cell(CELL_SIZE, DEAD_SYMBOL);
                btn.setActionCommand(i + "," + j);
                registerActionListenerForCellButton(btn);
                
                jPanelBoard.add(btn);

                jButtonCells[i][j] = btn;
                
                cells[i][j] = DEAD;
            }
        }
        
        pack();
    }
    
    private void markCell(JButton button, boolean isAlive)
    {
        int[] rowAndCol = readRowAndColOfTheCellClicked(button.getActionCommand());
        cells[rowAndCol[0]][rowAndCol[1]] = isAlive;
        button.setText((isAlive ? ALIVE_SYMBOL : DEAD_SYMBOL));
    }
    
    private int[] readRowAndColOfTheCellClicked(String rowAndColText)
    {
        int[] rowAndCol = new int[2];
        StringTokenizer stn = new StringTokenizer(rowAndColText, ",");
        rowAndCol[0] = Integer.parseInt(stn.nextToken());
        rowAndCol[1] = Integer.parseInt(stn.nextToken());
        
        return rowAndCol;
    }
    
    private void runAnimation()
    {
        RunAnimation animation = new RunAnimation();
        animation.execute();
    }
    
    private void initGUI() {
        initFormComponents();
        initForm();
        initLayoutForBoardPanel();
        initContentLayout();
        pack();
    }
    
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> new GameOfLife().setVisible(true));        
    }
    
    class RunAnimation extends SwingWorker<Void, Void>
    {
        @Override
        protected Void doInBackground() 
        {      
            do
            {
                publish();
                
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }while(jButtonRunGame.getText().equals(STOP_GAME));
            
            return null;
        }
        
        @Override
        protected void process(java.util.List<Void> values)
        {
            cells = GameBoard.nextBoard(cells);
            for (int i = 0; i < jButtonCells.length; i++) 
                for (int j = 0; j < jButtonCells[0].length; j++) 
                {                
                    markCell(jButtonCells[i][j], cells[i][j]);
                }
            
            pack();
        }
    }
    
    class Cell extends JButton
    {
        public Cell(int size, String textOnCell)
        {
            setText(textOnCell);
            makeSquaredButton(size);
            makeButtonLookLikeACell();
        }
        
        private void makeButtonLookLikeACell()
        {
            setMargin(new Insets(0, 0, 0, 0));
            setContentAreaFilled(false);
            setOpaque(false);
        }
        
        private void makeSquaredButton(int size)
        {
            Dimension d = super.getPreferredSize();
            d.height = size;
            d.width = size;
            setPreferredSize(d);
        }
    }
}