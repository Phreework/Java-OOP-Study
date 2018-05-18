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

	public static final int WIDTH = 400; // 面板宽
	public static final int HEIGHT = 654; // 面板高

	//状态机
	private int state;
	private static final int START = 0;
	private static final int RUNNING = 1;
	private static final int PAUSE = 2;
	private static final int GAME_OVER = 3;

	private int score = 0; // 得分
	private Timer timer; // 定时器
	private int interval = 1000 / 100; // 时间间隔(毫秒)

	public static BufferedImage background;
	public static BufferedImage start;
	public static BufferedImage airplane;
	public static BufferedImage bee;
	public static BufferedImage bullet;
	public static BufferedImage player0;
	public static BufferedImage player1;
	public static BufferedImage pause;
	public static BufferedImage gameover;

	private FlyObject[] enemys = {}; // 敌机数组
	private Bullet[] bullets = {}; // 子弹数组
	private Player player = new Player(); // 英雄机

	static { // 静态代码块，初始化图片资源
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

	//主程序入口
	public static void main(String[] args) {
		JFrame frame = new JFrame("Game");
		PlaneGame planegame = new PlaneGame(); // 面板对象
		frame.add(planegame); // 将面板添加到JFrame中
		frame.setSize(WIDTH, HEIGHT); // 设置大小
		frame.setAlwaysOnTop(true); // 设置其总在最上
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 默认关闭操作
		frame.setIconImage(new ImageIcon("images/icon.jpg").getImage()); // 设置窗体的图标
		frame.setLocationRelativeTo(null); // 设置窗体初始位置
		frame.setVisible(true); // 尽快调用paint

		planegame.action(); // 启动执行
	}


	
	@Override
	public void paint(Graphics g) {
		g.drawImage(background, 0, 0, null); // 画背景图
		paintplayer(g); // 画英雄机
		paintBullets(g); // 画子弹
		paintFlyObjects(g); // 画飞行物
		paintScore(g); // 画分数
		paintState(g); // 画游戏状态
	}

	/** 画英雄机 */
	public void paintplayer(Graphics g) {
		g.drawImage(player.getSprite(), player.getX(), player.getY(), null);
	}

	/** 画子弹 */
	public void paintBullets(Graphics g) {
		for (int i = 0; i < bullets.length; i++) {
			Bullet b = bullets[i];
			g.drawImage(b.getSprite(), b.getX() - b.getWidth() / 2, b.getY(), null);
		}
	}

	/** 画飞行物 */
	public void paintFlyObjects(Graphics g) {
		for (int i = 0; i < enemys.length; i++) {
			FlyObject f = enemys[i];
			g.drawImage(f.getSprite(), f.getX(), f.getY(), null);
		}
	}

	/** 画分数 */
	public void paintScore(Graphics g) {
		int x = 10; // x坐标
		int y = 25; // y坐标
		Font font = new Font(Font.SANS_SERIF, Font.BOLD, 14); // 字体
		g.setColor(new Color(0x3A3B3B));
		g.setFont(font); // 设置字体
		g.drawString("SCORE:" + score, x, y); // 画分数
		y += 20; // y坐标增20
		g.drawString("LIFE:" + player.getLife(), x, y); // 画命
	}

	/** 画游戏状态 */
	public void paintState(Graphics g) {
		switch (state) {
		case START: // 启动状态
			g.drawImage(start, 0, 0, null);
			break;
		case PAUSE: // 暂停状态
			g.drawImage(pause, 0, 0, null);
			break;
		case GAME_OVER: // 游戏终止状态
			g.drawImage(gameover, 0, 0, null);
			break;
		}
	}

	/** 启动执行代码 */
	public void action() {
		// 鼠标监听事件
		MouseAdapter l = new MouseAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) { // 鼠标移动
				if (state == RUNNING) { // 运行状态下移动英雄机--随鼠标位置
					int x = e.getX();
					int y = e.getY();
					player.moveTo(x, y);
				}
			}

			@Override
			public void mouseEntered(MouseEvent e) { // 鼠标进入
				if (state == PAUSE) { // 暂停状态下运行
					state = RUNNING;
				}
			}

			@Override
			public void mouseExited(MouseEvent e) { // 鼠标退出
				if (state != GAME_OVER && state != START) { // 游戏未结束，则设置其为暂停
					state = PAUSE;
				}
			}

			@Override
			public void mouseClicked(MouseEvent e) { // 鼠标点击
				switch (state) {
				case START:
					state = RUNNING; // 启动状态下运行
					break;
				case GAME_OVER: // 游戏结束，清理现场
					enemys = new FlyObject[0]; // 清空飞行物
					bullets = new Bullet[0]; // 清空子弹
					player = new Player(); // 重新创建英雄机
					score = 0; // 清空成绩
					state = START; // 状态设置为启动
					break;
				}
			}
		};
		this.addMouseListener(l); // 处理鼠标点击操作
		this.addMouseMotionListener(l); // 处理鼠标滑动操作

		timer = new Timer(); // 主流程控制
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				if (state == RUNNING) { // 运行状态
					enterAction(); // 飞行物入场
					stepAction(); // 走一步
					shootAction(); // 英雄机射击
					BulletHitAction(); // 子弹打飞行物
					isOutScreenAction(); // 删除越界飞行物及子弹
					checkGameOverAction(); // 检查游戏结束
				}
				repaint(); // 重绘，调用paint()方法
			}

		}, interval, interval);
	}

	int flyEnteredIndex = 0; // 飞行物入场计数

	/** 飞行物入场 */
	public void enterAction() {
		flyEnteredIndex++;
		if (flyEnteredIndex % 40 == 0) { // 400毫秒生成一个飞行物--10*40
			FlyObject obj = nextOne(); // 随机生成一个飞行物
			enemys = Arrays.copyOf(enemys, enemys.length + 1);
			enemys[enemys.length - 1] = obj;
		}
	}

	/** 步事件 */
	public void stepAction() {
		for (int i = 0; i < enemys.length; i++) { // 飞行物走一步
			FlyObject f = enemys[i];
			f.step();
		}

		for (int i = 0; i < bullets.length; i++) { // 子弹走一步
			Bullet b = bullets[i];
			b.step();
		}
		player.step(); // 英雄机走一步
	}

	/** 飞行物走一步 */
	public void flyingStepAction() {
		for (int i = 0; i < enemys.length; i++) {
			FlyObject f = enemys[i];
			f.step();
		}
	}

	int shootIndex = 0; // 射击计数

	/** 射击 */
	public void shootAction() {
		shootIndex++;
		if (shootIndex % 30 == 0) { // 300毫秒发一颗
			Bullet[] bs = player.shoot(); // 英雄打出子弹
			bullets = Arrays.copyOf(bullets, bullets.length + bs.length); // 扩容
			System.arraycopy(bs, 0, bullets, bullets.length - bs.length, bs.length); // 追加数组
		}
	}

	/** 子弹与飞行物碰撞检测 */
	public void BulletHitAction() {
		for (int i = 0; i < bullets.length; i++) { // 遍历所有子弹
			Bullet b = bullets[i];
			hit(b); // 子弹和飞行物之间的碰撞检查
		}
	}

	/** 删除越界飞行物及子弹 */
	public void isOutScreenAction() {
		int index = 0; // 索引
		FlyObject[] flyingLives = new FlyObject[enemys.length]; // 活着的飞行物
		for (int i = 0; i < enemys.length; i++) {
			FlyObject f = enemys[i];
			if (!f.isOutScreen()) {
				flyingLives[index++] = f; // 不越界的留着
			}
		}
		enemys = Arrays.copyOf(flyingLives, index); // 将不越界的飞行物都留着

		index = 0; // 索引重置为0
		Bullet[] bulletLives = new Bullet[bullets.length];
		for (int i = 0; i < bullets.length; i++) {
			Bullet b = bullets[i];
			if (!b.isOutScreen()) {
				bulletLives[index++] = b;
			}
		}
		bullets = Arrays.copyOf(bulletLives, index); // 将不越界的子弹留着
	}

	/** 检查游戏结束 */
	public void checkGameOverAction() {
		if (isGameOver()) {
			state = GAME_OVER; // 改变状态
		}
	}

	/** 检查游戏是否结束 */
	public boolean isGameOver() {

		for (int i = 0; i < enemys.length; i++) {
			int index = -1;
			FlyObject obj = enemys[i];
			if (player.hit(obj)) { // 检查英雄机与飞行物是否碰撞
				player.subtractLife(); // 减命
				player.setDoubleFire(0); // 双倍火力解除
				index = i; // 记录碰上的飞行物索引
			}
			if (index != -1) {
				FlyObject t = enemys[index];
				enemys[index] = enemys[enemys.length - 1];
				enemys[enemys.length - 1] = t; // 碰上的与最后一个飞行物交换

				enemys = Arrays.copyOf(enemys, enemys.length - 1); // 删除碰上的飞行物
			}
		}

		return player.getLife() <= 0;
	}

	/** 子弹和飞行物之间的碰撞检查 */
	public void hit(Bullet bullet) {
		int index = -1; // 击中的飞行物索引
		for (int i = 0; i < enemys.length; i++) {
			FlyObject obj = enemys[i];
			if (obj.isShootBy(bullet)) { // 判断是否击中
				index = i; // 记录被击中的飞行物的索引
				break;
			}
		}
		if (index != -1) { // 有击中的飞行物
			FlyObject one = enemys[index]; // 记录被击中的飞行物

			FlyObject temp = enemys[index]; // 被击中的飞行物与最后一个飞行物交换
			enemys[index] = enemys[enemys.length - 1];
			enemys[enemys.length - 1] = temp;

			enemys = Arrays.copyOf(enemys, enemys.length - 1); // 删除最后一个飞行物(即被击中的)

			// 检查one的类型(敌人加分，奖励获取)
			if (one instanceof Enemy) { // 检查类型，是敌人，则加分
				Enemy e = (Enemy) one; // 强制类型转换
				score += e.getScore(); // 加分
			}
			if (one instanceof Bee) { // 若为奖励，设置奖励
				Bee b = (Bee) one;
				int type = b.getType(); // 获取奖励类型
				switch (type) {
				case Award.DOUBLE_FIRE:
					player.addDoubleFire(); // 设置双倍火力
					break;
				case Award.LIFE:
					player.addLife(); // 设置加命
					break;
				}
			}
		}
	}

	/**
	 * 随机生成飞行物
	 * 
	 * @return 飞行物对象
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
