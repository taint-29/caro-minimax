package model;

import javax.swing.*;
import java.awt.*;

public class HowToPlayContent extends JLabel {

    private int width = 750;
    private int high = 500;
    private int x = 5;
    private int y = 0;

    public HowToPlayContent() {
        this.setBounds(x, y, width, high);
        this.setVerticalAlignment(JLabel.TOP);
        this.setHorizontalAlignment(JLabel.CENTER);
        this.setFont(new Font("", Font.BOLD, 20));
        this.setText("<HTML>"
                        + "<p>1. LUẬT CHƠI : </p>"

                        + "<p>- Ở Việt Nam, cờ này thường chơi trên giấy tập học sinh (đã có sẵn các ô ca-rô),"
                        + " dùng bút đánh dấu hình tròn (O) và chữ thập (X) để đại diện cho 2 quân cờ,"
                        + " bên nào có đường 5 quân liền nhau trên một hàng, một cột hoặc một đường chéo là thắng.</p>"
                        + "<br><p>2. CÁCH CHƠI : </p>"
                        + "<p>- Với mỗi ván chơi, người chơi sẽ chờ đến lượt đi quân cờ của mình (X hoặc O). Nhấn chuột trái vào ô muốn hạ cờ. Hãy sắp xếp các quân cờ của phe mình một cách hợp lý vừa chặn đường tiến công của quân địch vừa tạo nước thắng cho mình</p>"
                        + "<br><p>3. CƠ CHẾ XẾP HẠNG : </p>"
                        + "<p>- Hiệu số của mỗi người chơi được tính bằng hiệu giữa số lần thắng và số lần thua. Người chơi nào có hiệu số cao hơn sẽ có xếp hạng cao hơn."
        );

    }
}
