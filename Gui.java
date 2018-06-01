package sample;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class Gui extends JFrame{

    private int hour = 15;
    private int minute = 0;
    private int correct_Cnt = 0;
    private String rdm_RuRo = "";
    private ImageIcon img_Anger = new ImageIcon(this.getClass().getResource("./img/anger.PNG"));
    private ImageIcon img_Happy = new ImageIcon(this.getClass().getResource("./img/happy.PNG"));
    private ImageIcon img_Null = new ImageIcon(this.getClass().getResource("./img/null_bg.PNG"));
    private JLabel lbl_Timer;
    private JButton btn_Ro = new JButton("ろ");
    private JButton btn_Ru = new JButton("る");
    private JButton img_Btn_Anger = new JButton(img_Anger);
    private JButton img_Btn_Happy = new JButton("",img_Happy);
    private JLabel lbl_Correct = new JLabel("Start!!");
    private JLabel lbl_User_Correct_Count = new JLabel("あなたの現在の記録：" + correct_Cnt);
    private JLabel lbl_Description = new JLabel("ろ と る を見分けよう！");
    private JLabel lbl_TryIt = new JLabel("15分間やってみよう！");
    private JPanel pnl_Game_Description = new JPanel();

    // ウィンドウ本体
    public Gui() {

        // ウィンドウの閉じ方
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 位置とサイズ
        setBounds(100, 100, 740, 640);

        //レイアウト作成(3*3)
        setLayout(new GridLayout(3, 3));

        //タイトル設定
        setTitle("[る][ろ]ゲーム！不安性が治るかも？？");

        fontSizeChg_Btn(btn_Ro);
        fontSizeChg_Btn(btn_Ru);
        fontSizeChg(lbl_Correct);
        fontAlignChg(lbl_Correct);
        lbl_Correct.setForeground(new Color(204,255,0));
        fontSizeChg2(lbl_TryIt);
        fontSizeChg2(lbl_Description);
        fontSizeChg2(lbl_User_Correct_Count);
        lbl_User_Correct_Count.setHorizontalAlignment(JLabel.TRAILING);
        lbl_User_Correct_Count.setVerticalAlignment(JLabel.BOTTOM);

        String s = hour < 10 ? ("0" + hour + ":00") : (hour + ":00");//制限時間は変更可能
        lbl_Timer = new JLabel(s);
        fontAlignChg(lbl_Timer);
        fontSizeChg(lbl_Timer);

        JLabel lbl_null = new JLabel(" ");//GridLayout add対策用

        pnl_Game_Description.setBackground(new Color(102,204,255));
        pnl_Game_Description.add(lbl_Description);
        pnl_Game_Description.add(lbl_TryIt);

        getContentPane().setBackground(new Color(102,204,255));//画面 背景色設定

        add(pnl_Game_Description);  //1 GridLayer用
        add(img_Btn_Anger);         //2
        add(lbl_Timer);             //3
        add(btn_Ro);                //4
        add(lbl_Correct);           //5
        add(btn_Ru);                //6
        add(lbl_null);              //7
        add(img_Btn_Happy);         //8
        add(lbl_User_Correct_Count);//9

        setVisible(true);
        begin_hide();//初回画面表示要素の調整
        startTimer();//タイマーを自動でスタート
    }
    public static void fontSizeChg(JLabel lbl){
        lbl.setFont(new Font("ＭＳ ゴシック", Font.BOLD, 40));
        lbl.setForeground(new Color(102,255,204));
    }
    public static void fontSizeChg2(JLabel lbl){
        lbl.setFont(new Font("ＭＳ ゴシック", Font.BOLD, 20));
        lbl.setHorizontalAlignment(JLabel.LEADING);
        lbl.setVerticalAlignment(JLabel.TOP);
    }
    public static void fontSizeChg_Btn(JButton btn){
        btn.setFont(new Font("ＭＳ ゴシック", Font.BOLD, 40));
        btn.setForeground(new Color(102,255,204));
        btn.setBackground(new Color(0,204,255));
    }
    public static void fontAlignChg(JLabel lbl){
        lbl.setHorizontalAlignment(JLabel.CENTER);
        lbl.setVerticalAlignment(JLabel.CENTER);

    }
    public void startTimer() {
        //ろ ボタン押下時
        btn_Ro.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                answer_Correct_Judgment("ろ");
            }
        });
        //る ボタン押下時
        btn_Ru.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                answer_Correct_Judgment("る");
            }
        });
        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (minute == 0) {
                minute = 59;
                hour--;
            } else {
                minute--;
            }

            if(minute % 2 == 0){
                hideBtn_viewImg();
            }else{
                viewBtn_hideImg();
            }

            String h = hour < 10 ? ("0" + hour) : Integer.toString(hour);
            String m = minute < 10 ? ("0" + minute) : Integer.toString(minute);
            lbl_Timer.setText(h + ":" + m);
            if (hour == 0 && minute == 0) {
                showMsg();
                break;
            }
        }
    }
    public void answer_Correct_Judgment(String s_Ru_or_Ro) {
        if(rdm_RuRo.equals(s_Ru_or_Ro)){
            lbl_Correct.setText("正解！");
            correct_Cnt++;
            lbl_User_Correct_Count.setText("現在のあなたの記録：" + correct_Cnt);
        }
    }
    public void showMsg() {
        JOptionPane.showMessageDialog(null, "終了！お疲れ様でした！", "終了します",
        JOptionPane.INFORMATION_MESSAGE);
    }
    public void randomRuRoView() {
        double rdm = Math.random();
        rdm = rdm * 2;
        int rdmI = (int)rdm + 1;
        if(rdmI >= 2){
            rdm_RuRo = "ろ";
            img_Btn_Happy.setText(rdm_RuRo);
        }else{
            rdm_RuRo = "る";
            img_Btn_Happy.setText(rdm_RuRo);
        }
        img_Btn_Happy.setHorizontalTextPosition(JButton.CENTER);
        img_Btn_Happy.setVerticalTextPosition(JButton.CENTER);
        img_Btn_Happy.setFont(new Font("ＭＳ ゴシック", Font.BOLD, 40));
        img_Btn_Happy.setForeground(new Color(102,255,204));
    }

    public void hideBtn_viewImg() {
        //非表示
        config_Cng_RuRo_Btn(false, "", "");
        lbl_Correct.setText("");

        //表示
        config_Cng_Img_Btn(true, img_Anger, img_Happy);
        img_Btn_Happy.setText("");

    }

    public void viewBtn_hideImg() {
        //表示
        config_Cng_RuRo_Btn(true, "ろ", "る");

        //非表示
        config_Cng_Img_Btn(false, img_Null, img_Null);
        randomRuRoView();
    }

    public void begin_hide() {
        config_Cng_RuRo_Btn(false, "", "");
    }

    public void config_Cng_RuRo_Btn(boolean btn_Bln,String s_Ro, String s_Ru) {
         btn_Ro.setContentAreaFilled(btn_Bln);
         btn_Ru.setContentAreaFilled(btn_Bln);
         btn_Ro.setBorderPainted(btn_Bln);
         btn_Ru.setBorderPainted(btn_Bln);
         btn_Ro.setText(s_Ro);
         btn_Ru.setText(s_Ru);
    }

    public void config_Cng_Img_Btn(boolean btn_Bln, ImageIcon img_Icon_Anger, ImageIcon img_Icon_happy) {
        img_Btn_Anger.setContentAreaFilled(btn_Bln);
        img_Btn_Happy.setContentAreaFilled(btn_Bln);
        img_Btn_Anger.setBorderPainted(btn_Bln);
        img_Btn_Happy.setBorderPainted(btn_Bln);
        img_Btn_Anger.setIcon(img_Icon_Anger);
        img_Btn_Happy.setIcon(img_Icon_happy);
   }

    public static void main(String[] args) {
        Gui frm = new Gui();   // ウィンドウ作成
        frm.setVisible(true);  // 表示

    }
}
