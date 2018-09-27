package easyGA;

import java.util.Random;

public class EasyGA_lib {

	int kotai_num = 50;// 個体数
	int sedai_max = 2000;// 世代数
	int item_num = 10;// 商品数
	int cros_rate = 90;// 交差率
	int muta_rate = 5;// 突然変異率
	int gene[][];// 個体群を代入する二次元配列
	int next_gene[][];// 次世代の個体群を代入する二次元配列
	double[] score;

	public EasyGA_lib(int kotai_num, int sedai_max, int item_num, int cros_rate, int muta_rate) {
		// super();
		this.kotai_num = kotai_num;
		this.sedai_max = sedai_max;
		this.item_num = item_num;
		this.cros_rate = cros_rate;
		this.muta_rate = muta_rate;

		this.gene = new int[kotai_num][item_num];
		this.next_gene = new int[kotai_num][item_num];

		// 初期個体の生成
		for (int i = 0; i < kotai_num; i++) {
			for (int j = 0; j < item_num; j++) {
				Random rand = new Random();
				gene[i][j] = rand.nextInt(10);
			}
		}

		this.score = new double[kotai_num];
		for (int sedai = 0; sedai < sedai_max; sedai++) {
			shinka(sedai);
			roolet();
			kousa();
			toshizen();
			irekae();
		}

	}

	public void shinka(int sedai) {// ここから進化繰り返し
		// TODO Auto-generated method stub
		// 各個体の採点
		for (int i = 0; i < kotai_num; i++) {
			score[i] = 0.0;
			for (int j = 0; j < item_num; j++) {
				score[i] += gene[i][j] * gene[i][j];
			}
		}

		// 最高得点の個体を選ぶ
		int max = 0;// 最初に0番目の個体を1番としておく
		for (int i = 0; i < kotai_num - 1; i++) {
			if (score[max] < score[i + 1])
				max = i + 1;
		}
		// 最高得点個体の書き出し
		if (sedai % 200 == 0) {
			System.out.print(sedai + ":");
			for (int i = 0; i < item_num; i++) {
				System.out.print(gene[max][i]);
			}
			System.out.println("," + score[max]);
		}

	}

	public void roolet() {
		// TODO Auto-generated method stub
		// ルーレット選択
		int total = 0;
		for (int i = 0; i < kotai_num; i++) {
			total += score[i];
		}
		// 個体数分のルーレット選択を行う
		int ya_kotai = 0;
		for (int i = 0; i < kotai_num; i++) {
			Random rand = new Random();
			int ya = rand.nextInt(total);// 矢を放つ
			int tumiage = 0;// 矢が当たったのは何番目の個体であるか？
			for (int j = 0; j < kotai_num; j++) {
				tumiage += score[j];
				if (tumiage > ya) {
					ya_kotai = j;
					break;
				}
			}
			for (int j = 0; j < item_num; j++) {
				next_gene[i][j] = gene[ya_kotai][j];
			}
		}
	}

	private void kousa() {// 交差処理
		// TODO Auto-generated method stub
		int cross_num = kotai_num / 2;
		for (int i = 0; i < cross_num; i++) {
			Random rand = new Random();
			int cf = rand.nextInt(100);
			// 出てきた数以下であれば交差する
			if (cf < cros_rate) {
				// 一点交差の位置を決める
				int c_pos = rand.nextInt(item_num);
				// 交叉位置から最後までを入れ替える
				for (int j = 0; j < item_num - c_pos; j++) {
					int temp = next_gene[i * 2][j + c_pos];
					next_gene[i * 2][j + c_pos] = next_gene[i * 2 + 1][j + c_pos];
					next_gene[i * 2 + 1][j + c_pos] = temp;
				}
			}
		}
	}

	private void toshizen() {
		// TODO Auto-generated method stub
		for (int i = 0; i < kotai_num; i++) {
			for (int j = 0; j < item_num; j++) {
				Random rand = new Random();
				int cf = rand.nextInt(100);
				if (cf < muta_rate) {
					next_gene[i][j] = rand.nextInt(10);
				}
			}
		}
	}

	private void irekae() {
		// TODO Auto-generated method stub
		for (int i = 0; i < kotai_num; i++) {
			for (int j = 0; j < item_num; j++) {
				gene[i][j] = next_gene[i][j];
			}
		}
	}

}
