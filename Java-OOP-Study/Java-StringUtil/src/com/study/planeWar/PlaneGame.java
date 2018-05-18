package com.study.planeWar;

import java.awt.Font;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class PlaneGame extends JPanel {

	public static final int WIDTH = 400; // ����
	public static final int HEIGHT = 654; // ����

	//״̬��
	private int state;
	private static final int START = 0;
	private static final int RUNNING = 1;
	private static final int PAUSE = 2;
	private static final int GAME_OVER = 3;

	private int score = 0; // �÷�
	private Timer timer; // ��ʱ��
	private int interval = 1000 / 100; // ʱ����(����)

	public static BufferedImage background;
	public static BufferedImage start;
	public static BufferedImage airplane;
	public static BufferedImage bee;
	public static BufferedImage bullet;
	public static BufferedImage player0;
	public static BufferedImage player1;
	public static BufferedImage pause;
	public static BufferedImage gameover;

	private FlyObject[] enemys = {}; // �л�����
	private Bullet[] bullets = {}; // �ӵ�����
	private Player player = new Player(); // Ӣ�ۻ�

	static { // ��̬����飬��ʼ��ͼƬ��Դ
		try {
			background = ImageIO.read(PlaneGame.class.getResource("/background.png"));
			start = ImageIO.read(PlaneGame.class.getResource("/start.png"));
			airplane = ImageIO.read(PlaneGame.class.getResource("/airplane.png"));
			bee = ImageIO.read(PlaneGame.class.getResource("/bee.png"));
			bullet = ImageIO.read(PlaneGame.class.getResource("/bullet.png"));
			player0 = ImageIO.read(PlaneGame.class.getResource("/player0.png"));
			player1 = ImageIO.read(PlaneGame.class.getResource("/player1.png"));
			pause = ImageIO.read(PlaneGame.class.getResource("/pause.png"));
			gameover = ImageIO.read(PlaneGame.class.getResource("/gameover.png"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//���������
	public static void main(String[] args) {
		JFrame frame = new JFrame("Game");
		PlaneGame planegame = new PlaneGame(); // ������
		frame.add(planegame); // ��������ӵ�JFrame��
		frame.setSize(WIDTH, HEIGHT); // ���ô�С
		frame.setAlwaysOnTop(true); // ��������������
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Ĭ�Ϲرղ���
		frame.setIconImage(new ImageIcon("images/icon.jpg").getImage()); // ���ô����ͼ��
		frame.setLocationRelativeTo(null); // ���ô����ʼλ��
		frame.setVisible(true); // �������paint

		planegame.action(); // ����ִ��
	}


	
	@Override
	public void paint(Graphics g) {
		g.drawImage(background, 0, 0, null); // ������ͼ
		paintplayer(g); // ��Ӣ�ۻ�
		paintBullets(g); // ���ӵ�
		paintFlyObjects(g); // ��������
		paintScore(g); // ������
		paintState(g); // ����Ϸ״̬
	}

	/** ��Ӣ�ۻ� */
	public void paintplayer(Graphics g) {
		g.drawImage(player.getSprite(), player.getX(), player.getY(), null);
	}

	/** ���ӵ� */
	public void paintBullets(Graphics g) {
		for (int i = 0; i < bullets.length; i++) {
			Bullet b = bullets[i];
			g.drawImage(b.getSprite(), b.getX() - b.getWidth() / 2, b.getY(), null);
		}
	}

	/** �������� */
	public void paintFlyObjects(Graphics g) {
		for (int i = 0; i < enemys.length; i++) {
			FlyObject f = enemys[i];
			g.drawImage(f.getSprite(), f.getX(), f.getY(), null);
		}
	}

	/** ������ */
	public void paintScore(Graphics g) {
		int x = 10; // x����
		int y = 25; // y����
		Font font = new Font(Font.SANS_SERIF, Font.BOLD, 14); // ����
		g.setColor(new Color(0x3A3B3B));
		g.setFont(font); // ��������
		g.drawString("SCORE:" + score, x, y); // ������
		y += 20; // y������20
		g.drawString("LIFE:" + player.getLife(), x, y); // ����
	}

	/** ����Ϸ״̬ */
	public void paintState(Graphics g) {
		switch (state) {
		case START: // ����״̬
			g.drawImage(start, 0, 0, null);
			break;
		case PAUSE: // ��ͣ״̬
			g.drawImage(pause, 0, 0, null);
			break;
		case GAME_OVER: // ��Ϸ��ֹ״̬
			g.drawImage(gameover, 0, 0, null);
			break;
		}
	}

	/** ����ִ�д��� */
	public void action() {
		// �������¼�
		MouseAdapter l = new MouseAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) { // ����ƶ�
				if (state == RUNNING) { // ����״̬���ƶ�Ӣ�ۻ�--�����λ��
					int x = e.getX();
					int y = e.getY();
					player.moveTo(x, y);
				}
			}

			@Override
			public void mouseEntered(MouseEvent e) { // ������
				if (state == PAUSE) { // ��ͣ״̬������
					state = RUNNING;
				}
			}

			@Override
			public void mouseExited(MouseEvent e) { // ����˳�
				if (state != GAME_OVER && state != START) { // ��Ϸδ��������������Ϊ��ͣ
					state = PAUSE;
				}
			}

			@Override
			public void mouseClicked(MouseEvent e) { // �����
				switch (state) {
				case START:
					state = RUNNING; // ����״̬������
					break;
				case GAME_OVER: // ��Ϸ�����������ֳ�
					enemys = new FlyObject[0]; // ��շ�����
					bullets = new Bullet[0]; // ����ӵ�
					player = new Player(); // ���´���Ӣ�ۻ�
					score = 0; // ��ճɼ�
					state = START; // ״̬����Ϊ����
					break;
				}
			}
		};
		this.addMouseListener(l); // �������������
		this.addMouseMotionListener(l); // ������껬������

		timer = new Timer(); // �����̿���
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				if (state == RUNNING) { // ����״̬
					enterAction(); // �������볡
					stepAction(); // ��һ��
					shootAction(); // Ӣ�ۻ����
					BulletHitAction(); // �ӵ��������
					isOutScreenAction(); // ɾ��Խ������Ｐ�ӵ�
					checkGameOverAction(); // �����Ϸ����
				}
				repaint(); // �ػ棬����paint()����
			}

		}, interval, interval);
	}

	int flyEnteredIndex = 0; // �������볡����

	/** �������볡 */
	public void enterAction() {
		flyEnteredIndex++;
		if (flyEnteredIndex % 40 == 0) { // 400��������һ��������--10*40
			FlyObject obj = nextOne(); // �������һ��������
			enemys = Arrays.copyOf(enemys, enemys.length + 1);
			enemys[enemys.length - 1] = obj;
		}
	}

	/** ���¼� */
	public void stepAction() {
		for (int i = 0; i < enemys.length; i++) { // ��������һ��
			FlyObject f = enemys[i];
			f.step();
		}

		for (int i = 0; i < bullets.length; i++) { // �ӵ���һ��
			Bullet b = bullets[i];
			b.step();
		}
		player.step(); // Ӣ�ۻ���һ��
	}

	/** ��������һ�� */
	public void flyingStepAction() {
		for (int i = 0; i < enemys.length; i++) {
			FlyObject f = enemys[i];
			f.step();
		}
	}

	int shootIndex = 0; // �������

	/** ��� */
	public void shootAction() {
		shootIndex++;
		if (shootIndex % 30 == 0) { // 300���뷢һ��
			Bullet[] bs = player.shoot(); // Ӣ�۴���ӵ�
			bullets = Arrays.copyOf(bullets, bullets.length + bs.length); // ����
			System.arraycopy(bs, 0, bullets, bullets.length - bs.length, bs.length); // ׷������
		}
	}

	/** �ӵ����������ײ��� */
	public void BulletHitAction() {
		for (int i = 0; i < bullets.length; i++) { // ���������ӵ�
			Bullet b = bullets[i];
			hit(b); // �ӵ��ͷ�����֮�����ײ���
		}
	}

	/** ɾ��Խ������Ｐ�ӵ� */
	public void isOutScreenAction() {
		int index = 0; // ����
		FlyObject[] flyingLives = new FlyObject[enemys.length]; // ���ŵķ�����
		for (int i = 0; i < enemys.length; i++) {
			FlyObject f = enemys[i];
			if (!f.isOutScreen()) {
				flyingLives[index++] = f; // ��Խ�������
			}
		}
		enemys = Arrays.copyOf(flyingLives, index); // ����Խ��ķ����ﶼ����

		index = 0; // ��������Ϊ0
		Bullet[] bulletLives = new Bullet[bullets.length];
		for (int i = 0; i < bullets.length; i++) {
			Bullet b = bullets[i];
			if (!b.isOutScreen()) {
				bulletLives[index++] = b;
			}
		}
		bullets = Arrays.copyOf(bulletLives, index); // ����Խ����ӵ�����
	}

	/** �����Ϸ���� */
	public void checkGameOverAction() {
		if (isGameOver()) {
			state = GAME_OVER; // �ı�״̬
		}
	}

	/** �����Ϸ�Ƿ���� */
	public boolean isGameOver() {

		for (int i = 0; i < enemys.length; i++) {
			int index = -1;
			FlyObject obj = enemys[i];
			if (player.hit(obj)) { // ���Ӣ�ۻ���������Ƿ���ײ
				player.subtractLife(); // ����
				player.setDoubleFire(0); // ˫���������
				index = i; // ��¼���ϵķ���������
			}
			if (index != -1) {
				FlyObject t = enemys[index];
				enemys[index] = enemys[enemys.length - 1];
				enemys[enemys.length - 1] = t; // ���ϵ������һ�������ｻ��

				enemys = Arrays.copyOf(enemys, enemys.length - 1); // ɾ�����ϵķ�����
			}
		}

		return player.getLife() <= 0;
	}

	/** �ӵ��ͷ�����֮�����ײ��� */
	public void hit(Bullet bullet) {
		int index = -1; // ���еķ���������
		for (int i = 0; i < enemys.length; i++) {
			FlyObject obj = enemys[i];
			if (obj.isShootBy(bullet)) { // �ж��Ƿ����
				index = i; // ��¼�����еķ����������
				break;
			}
		}
		if (index != -1) { // �л��еķ�����
			FlyObject one = enemys[index]; // ��¼�����еķ�����

			FlyObject temp = enemys[index]; // �����еķ����������һ�������ｻ��
			enemys[index] = enemys[enemys.length - 1];
			enemys[enemys.length - 1] = temp;

			enemys = Arrays.copyOf(enemys, enemys.length - 1); // ɾ�����һ��������(�������е�)

			// ���one������(���˼ӷ֣�������ȡ)
			if (one instanceof Enemy) { // ������ͣ��ǵ��ˣ���ӷ�
				Enemy e = (Enemy) one; // ǿ������ת��
				score += e.getScore(); // �ӷ�
			}
			if (one instanceof Bee) { // ��Ϊ���������ý���
				Bee b = (Bee) one;
				int type = b.getType(); // ��ȡ��������
				switch (type) {
				case Award.DOUBLE_FIRE:
					player.addDoubleFire(); // ����˫������
					break;
				case Award.LIFE:
					player.addLife(); // ���ü���
					break;
				}
			}
		}
	}

	/**
	 * ������ɷ�����
	 * 
	 * @return ���������
	 */
	public static FlyObject nextOne() {
		Random random = new Random();
		int type = random.nextInt(20); // [0,20)
		if (type == 0) {
			return new Bee();
		} else {
			return new NormalEnemy();
		}
	}
}