package easyGA;

public class Easy_main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int kotai_num = 50;// 個体数
		int sedai_max = 2000;// 世代数
		int item_num = 10;// 商品数
		int cros_rate = 90;// 交差率
		int muta_rate = 5;// 突然変異率

		EasyGA_lib glib = new EasyGA_lib(kotai_num, sedai_max, item_num, cros_rate, muta_rate);

	}

}
