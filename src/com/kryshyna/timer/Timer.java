package com.kryshyna.timer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.TimeUnit;

/**
 * @author Vadym Kryshyna
 * Date: 01.10.14
 * Time: 11:55
 */
public class Timer extends JFrame {

    public static void main(String[] args) {
        JFrame frame = new Timer();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public Timer() {
        initComponents();
    }

    private void initComponents() {
        menuBar = new JMenuBar();
        menu = new JMenu();
        menuItemAbout = new JMenuItem();
        panelTime = new JPanel();
        buttonStart = new JButton("Start");
        buttonStop = new JButton("Stop");
        label1 = new JLabel(startTime);
        timer = null;

        Container contentPane = getContentPane();

        menu.setText("Menu");
        menuItemAbout.setText("About");
        menu.add(menuItemAbout);
        menuBar.add(menu);
        setJMenuBar(menuBar);

        buttonStart.setFont(buttonStart.getFont().deriveFont(buttonStart.getFont().getSize() + 4f));
        buttonStop.setFont(buttonStop.getFont().deriveFont(buttonStop.getFont().getSize() + 4f));
        label1.setFont(label1.getFont().deriveFont(label1.getFont().getSize() + 10f));
        label1.setHorizontalAlignment(SwingConstants.CENTER);

        //disabled button stop
        buttonStop.setEnabled(false);

        GroupLayout panelTimeLayout = new GroupLayout(panelTime);
        panelTime.setLayout(panelTimeLayout);
        panelTimeLayout.setHorizontalGroup(
                panelTimeLayout.createParallelGroup()
                        .addGroup(panelTimeLayout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addComponent(buttonStart, GroupLayout.PREFERRED_SIZE, 114, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 49, Short.MAX_VALUE)
                                .addComponent(buttonStop, GroupLayout.PREFERRED_SIZE, 114, GroupLayout.PREFERRED_SIZE)
                                .addGap(27, 27, 27))
                        .addGroup(panelTimeLayout.createSequentialGroup()
                                .addGap(39, 39, 39)
                                .addComponent(label1, GroupLayout.PREFERRED_SIZE, 244, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(51, Short.MAX_VALUE))
        );
        panelTimeLayout.setVerticalGroup(
                panelTimeLayout.createParallelGroup()
                        .addGroup(GroupLayout.Alignment.TRAILING, panelTimeLayout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addComponent(label1, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 132, Short.MAX_VALUE)
                                .addGroup(panelTimeLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(buttonStart, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(buttonStop, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE))
                                .addGap(25, 25, 25))
        );

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
                contentPaneLayout.createParallelGroup()
                        .addComponent(panelTime, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        contentPaneLayout.setVerticalGroup(
                contentPaneLayout.createParallelGroup()
                        .addComponent(panelTime, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pack();
        setLocationRelativeTo(getOwner());

        menuItemAbout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, messageAbout);
            }
        });

        buttonStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                label1.setText(startTime);
                buttonStart.setEnabled(false);
                buttonStop.setEnabled(true);
                final long start = System.currentTimeMillis();
                final long[] current = {System.currentTimeMillis()};
                timer = new javax.swing.Timer(100, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        current[0] = System.currentTimeMillis();
                        long time = current[0] - start;
                        setTime(time);
                        System.out.println(time+"");
                    }
                });
                timer.start();
            }
        });

        buttonStop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timer.stop();
                timer = null;
                buttonStop.setEnabled(false);
                buttonStart.setEnabled(true);

            }
        });
    }

    //set text with time on form
    private void setTime(long time){
        String t = String.format("%02d:%02d:%02d:%03d",
                TimeUnit.MILLISECONDS.toHours(time),
                TimeUnit.MILLISECONDS.toMinutes(time) -
                TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(time)),
                TimeUnit.MILLISECONDS.toSeconds(time)-
                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(time)),
                TimeUnit.MILLISECONDS.toMillis(time)-
                TimeUnit.SECONDS.toMillis(TimeUnit.MILLISECONDS.toSeconds(time)));
        label1.setText(t);
    }

    private JMenuBar menuBar;
    private JMenu menu;
    private JMenuItem menuItemAbout;
    private JPanel panelTime;
    private JButton buttonStart;
    private JButton buttonStop;
    private JLabel label1;
    private javax.swing.Timer timer;
    private String startTime = "00:00:00:000";
    private String messageAbout = "@Author: Vadym Kryshyna \n     2014";
}
