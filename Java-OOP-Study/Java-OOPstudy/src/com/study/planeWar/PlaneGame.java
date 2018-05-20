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

/**
 * 
 * @author Phree 
 */
public class PlaneGame extends JPanel {

	public static final int WIDTH = 400; // ��
	public static final int HEIGHT = 654; // ��

	// ��״̬��
	private int state;
	private static final int START = 0;
	private static final int RUNNING = 1;
	private static final int PAUSE = 2;
	private static final int GAMEOVER = 3;

	private int score = 0;
	private int highScore = 0;
	private Timer timer; // ��ʱ������
	private int period = 10; // �߳�ִ�м����10����

	//������Ϸ�ѶȵĹ���
	private static GameManager gameManager = new GameManager();


	// ͼƬ��Դ
	public static BufferedImage background;
	public static BufferedImage start;
	public static BufferedImage airplane;
	public static BufferedImage bee;
	public static BufferedImage bullet;
	public static BufferedImage player0;
	public static BufferedImage player1;
	public static BufferedImage pause;
	public static BufferedImage gameover;
	public static BufferedImage superenemy;
	public static BufferedImage enemybullet;

	private FlyObject[] enemys = {}; // �л�����
	private Bullet[] bullets = {}; // �ӵ�����
	private EnemyBullet[] ebullets = {};
	private Player player = new Player(); // ��Ҷ���

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
			superenemy = ImageIO.read(PlaneGame.class.getResource("/superenemy.png"));
			enemybullet = ImageIO.read(PlaneGame.class.getResource("/enemybullet.png"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// ���������
	public static void main(String[] args) {
		JFrame frame = new JFrame("Game");
		PlaneGame planegame = new PlaneGame();
		frame.add(planegame);
		frame.setSize(WIDTH, HEIGHT); // ���ÿ���
		frame.setAlwaysOnTop(true); // ��������������
		frame.setIconImage(new ImageIcon("images/icon.jpg").getImage()); // ����ͼ��
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null); 
		frame.setVisible(true); 

		planegame.action(); 
	}

	@Override
	public void paint(Graphics g) {
		paintBackground(g); // ������ͼ
		paintPlayer(g); // ��Ӣ�ۻ�
		paintBullets(g); // ���ӵ�
		paintFlyObjects(g); // ��������
		paintInfo(g); // ������
		paintState(g); // ����Ϸ״̬
	}

	public void paintBackground(Graphics g) {
		g.drawImage(background, 0, 0, null);
	}

	public void paintPlayer(Graphics g) {
		g.drawImage(player.getSprite(), player.getX(), player.getY(), null);
	}

	public void paintBullets(Graphics g) {
		for (int i = 0; i < bullets.length; i++) {
			Bullet b = bullets[i];
			g.drawImage(b.getSprite(), b.getX() - b.getWidth() / 2, b.getY(), null);
		}
		for (int i = 0; i < ebullets.length; i++) {
			EnemyBullet b = ebullets[i];
			g.drawImage(b.getSprite(), b.getX() - b.getWidth() / 2, b.getY(), null);
		}
	}

	public void paintFlyObjects(Graphics g) {
		for (int i = 0; i < enemys.length; i++) {
			FlyObject f = enemys[i];
			g.drawImage(f.getSprite(), f.getX(), f.getY(), null);
		}
	}

	public void paintInfo(Graphics g) {
		int x = 10; // x����
		int y = 25; // y����
		Font font = new Font(Font.SANS_SERIF, Font.BOLD, 14); // ����
		g.setColor(new Color(0x3A3B3B));
		g.setFont(font); // ��������
		g.drawString("��ǰ����: " + score, x, y); // ������
		y += 20;
		g.drawString("����:        " + player.getLife(), x, y); // ����
		y += 20;
		g.drawString("�Ѷ�:       " + gameManager.getRankinfo(), x, y); // ����
		y += 20;
		g.drawString("��߷�:     " + highScore, x, y); // ������
	}

	public void paintState(Graphics g) {
		switch (state) {
		case START: // ����״̬
			g.drawImage(start, 0, 0, null);
			int x = 135; // x����
			int y = 340; // y����
			Font font = new Font(Font.SANS_SERIF, Font.BOLD, 30); // ����
			g.setColor(new Color(0x3A3B3B));
			g.setFont(font); // ��������
			g.drawString("��߷�: " + highScore, x, y); // ������
			break;
		case PAUSE: // ��ͣ״̬
			g.drawImage(pause, 0, 0, null);
			break;
		case GAMEOVER: // ��Ϸ��ֹ״̬
			g.drawImage(gameover, 0, 0, null);
			break;
		}
	}

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
			public void mouseEntered(MouseEvent e) { // ��ͣ��������
				if (state == PAUSE) { // ��ͣ״̬�л�
					state = RUNNING;
				}
			}

			@Override
			public void mouseExited(MouseEvent e) { // ����˳���ͣ
				if (state != GAMEOVER && state != START)
					state = PAUSE;
			}

			@Override
			public void mouseClicked(MouseEvent e) { // ���������ʼ
				switch (state) {
				case START:
					state = RUNNING; // ����״̬������
					break;
				case GAMEOVER: // ��Ϸ�������³�ʼ��
					enemys = new FlyObject[0];
					bullets = new Bullet[0];
					ebullets = new EnemyBullet[0];
					player = new Player();
					score = 0;
					state = START;
					break;
				}
			}
		};
		this.addMouseListener(l); // ������
		this.addMouseMotionListener(l); // ��껬��

		timer = new Timer(); // �����̿���
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				if (state == RUNNING) { // ����״̬
					rankConfirmAction(); //�����Ϸ�ȼ�
					enterAction(); // �������볡
					stepAction(); // ��һ��
					shootAction(); // ���
					BulletHitAction(); // �ӵ���ײ���
					isOutScreenAction(); // ɾ����������Ｐ�ӵ�
					checkPlayerHitAction(); //��������Ƿ�ײ���л�
					checkGameOverAction(); // �����Ϸ����
				}
				repaint(); // �ػ�
			}
		}, period, period);
	}

	int flyEnteredIndex = 0; // �������볡����
	/** 
	 * ��Ϸ�Ѷȼ��
	 */
	public void rankConfirmAction() {
		gameManager.checkRank(score);
	}

	/**
	 *  ����������
	 */
	public void enterAction() {
		flyEnteredIndex++;
		if (flyEnteredIndex % 40 != 0)
			return;

		FlyObject obj = nextEnemy(); // �������һ��������
		enemys = Arrays.copyOf(enemys, enemys.length + 1);
		enemys[enemys.length - 1] = obj;

	}

	/** 
	 * ���¼� 
	 */
	public void stepAction() {
		for (int i = 0; i < enemys.length; i++) {
			FlyObject f = enemys[i];
			f.step();
		}

		for (int i = 0; i < bullets.length; i++) {
			Bullet b = bullets[i];
			b.step();
		}

		for (int i = 0; i < ebullets.length; i++) {
			EnemyBullet b = ebullets[i];
			b.step();
		}

		player.step();
	}

	/** 
	 * �����ﲽ�¼�
	 */
	public void flyingStepAction() {
		for (int i = 0; i < enemys.length; i++) {
			FlyObject f = enemys[i];
			f.step();
		}
	}

	int shootIndex = 0; // �������

	/** 
	 * ��� 
	 */
	public void shootAction() {
		shootIndex++;
		if (shootIndex % 30 == 0) { // ÿ30���뷢��
			Bullet[] bs = player.shoot();
			bullets = Arrays.copyOf(bullets, bullets.length + bs.length); // ����
			System.arraycopy(bs, 0, bullets, bullets.length - bs.length, bs.length); // ׷������
		}

		for (FlyObject e : enemys) {
			if (!(e instanceof SuperEnemy))
				continue;
			if (e.getY() != 50)
				continue;
			SuperEnemy se = (SuperEnemy) e;
			EnemyBullet[] bs = se.shoot();
			ebullets = Arrays.copyOf(ebullets, ebullets.length + bs.length); // ����
			System.arraycopy(bs, 0, ebullets, ebullets.length - bs.length, bs.length); // ׷������
		}
	}

	/** 
	 * �ӵ����������ײ��� 
	 */
	public void BulletHitAction() {
		for (Bullet b : bullets) { // ���������ӵ�
			hit(b); // �ӵ��ͷ�����֮�����ײ���
		}
		for (EnemyBullet eb : ebullets) {
			hitPlayer(eb);
		}

	}

	/** 
	 * ɾ��Խ������Ｐ�ӵ�
	 */
	public void isOutScreenAction() {
		
		int index = 0; // ����
		
		//ѭ���ų���Խ��ɻ�
		FlyObject[] flyingLives = new FlyObject[enemys.length];
		for(FlyObject f : enemys){
			if (!f.isOutScreen()) {
				flyingLives[index++] = f; // ��Խ�������
			}
		}
		enemys = Arrays.copyOf(flyingLives, index); // ��Խ��ķ�����

		//ѭ���ų���Խ���ӵ�
		index = 0; // ��������Ϊ0
		Bullet[] bulletLives = new Bullet[bullets.length];
		for(Bullet b: bullets){
			if (!b.isOutScreen()) {
				bulletLives[index++] = b;
			}
		}
		bullets = Arrays.copyOf(bulletLives, index); // ��Խ����ӵ�

		//ѭ���ų���Խ������ӵ�
		index = 0; // ��������Ϊ0
		EnemyBullet[] ebulletLives = new EnemyBullet[ebullets.length];
		for(EnemyBullet b: ebullets){
			if (!b.isOutScreen()) {
				ebulletLives[index++] = b;
			}
		}
		ebullets = Arrays.copyOf(ebulletLives, index); // ��Խ��ĵ����ӵ�
	}

	/**
	 * ��������������ײ��ɾ����ײ�ĵ���
	 */
	public void checkPlayerHitAction() {

		for (int i = 0; i < enemys.length; i++) {
			int index = -1;
			FlyObject obj = enemys[i];
			if (player.hit(obj)) { // ��������������Ƿ���ײ
				player.subtractLife(); // ����
				player.setDoubleFire(0); // ˫���������
				index = i; // ��¼���ϵķ���������
			}
			if (index != -1) {
				deleteEnemyHited(index);
			}
		}
	}
	/** 
	 * �����Ϸ���� 
	 */
	public void checkGameOverAction() {
		
		if (isGameOver()) {
			highScore = score;
			state = GAMEOVER; // �ı�״̬
		}
	}


	private boolean isGameOver() {
		return player.getLife() <= 0;
	}

	/** 
	 * �ӵ��ͷ�����֮�����ײ���
	 */
	public void hit(Bullet bullet) {
		int index = -1; // ����
		for (int i = 0; i < enemys.length; i++) {
			FlyObject obj = enemys[i];
			if (obj.isShootBy(bullet)) { // �ж��Ƿ����
				index = i;
				break;
			}
		}

		if (index == -1) return; // û�����˳�����
		
		FlyObject fly = enemys[index]; // ��¼�����еķ�����

		if (fly instanceof SuperEnemy) {		//�������˿�Ѫ���������ӵ�
			if (((SuperEnemy) fly).life != 0) {
				((SuperEnemy) fly).life -= 1;
				deleteBulletHited(bullet);
				return;
			}
		}

		deleteEnemyHited(index);
		// �ӷ�,��ȡ����
		getScoreAndAward(fly);

		deleteBulletHited(bullet);
	}


	/** 
	 * ������ɵ��� 
	 */
	public static FlyObject nextEnemy() {
		return gameManager.getNextEnemy();		
	}

	
	//���·�����Ϊ��߿ɶ��������ع�����
	
	/**Extra Method ɾ��������ײ���ӵ�*/
	private void deleteBulletHited(Bullet bullet) {
		for (int i = 0; i < bullets.length; i++) {
			if (bullets[i] != bullet)
				continue;
			Bullet temp = bullets[i]; // �����еķ����������һ����
			bullets[i] = bullets[bullets.length - 1];
			bullets[bullets.length - 1] = temp;
			bullets = Arrays.copyOf(bullets, bullets.length - 1); // ɾ�������еķ�����
		}
	}
	/**Extra Method ��ҵ÷��뽱��*/
	private void getScoreAndAward(FlyObject fly) {
		if (fly instanceof Enemy) {
			Enemy e = (Enemy) fly;
			score += e.getScore();
		}
		if (fly instanceof Bee) {
			Bee b = (Bee) fly;
			int type = b.getType();
			switch (type) {
			case Award.DOUBLE_FIRE:
				player.addDoubleFire();
				break;
			case Award.LIFE:
				player.addLife();
				break;
			}
		}
	}
	/**Extra Method ɾ��������ײ�ĵл�*/
	private void deleteEnemyHited(int index) {
		FlyObject temp = enemys[index]; // �����еķ����������һ����
		enemys[index] = enemys[enemys.length - 1];
		enemys[enemys.length - 1] = temp;
		enemys = Arrays.copyOf(enemys, enemys.length - 1); // ɾ�������еķ�����
	}
	/**Extra Method �����ײ���*/
	private void hitPlayer(EnemyBullet eb) {
		// TODO Auto-generated method stub
		if (!player.isShootBy(eb))
			return;

		player.subtractLife();

		deleteEnemyBulletHited(eb);

	}
	/**Extra Method ɾ��������ײ�ĵз��ӵ�*/
	private void deleteEnemyBulletHited(EnemyBullet eb) {
		int index = -1;
		for (int i = 0; i < ebullets.length; i++) {
			if (eb == ebullets[i]) {
				index = i;
				break;
			}
		}
		if (index == -1)
			return;
		EnemyBullet e = ebullets[index];
		ebullets[index] = ebullets[ebullets.length - 1];
		ebullets[ebullets.length - 1] = e; // ���ϵ������һ�������ｻ��

		ebullets = Arrays.copyOf(ebullets, ebullets.length - 1); // ɾ�����ϵķ�����
	}

}