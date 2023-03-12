package service;

public class BMIService {

	public String getBmi(String height, String weight) {
		// 5.確認是否有參數?
		if (height == null || weight == null) {
			return "無參數或參數數量不正確";
		}
		// 6.進行轉型 String -> double
		double h = 0, w = 0;
		try {
			h = Double.parseDouble(height);
			w = Double.parseDouble(weight);
		} catch (Exception e) {
			return "請輸入正確數值資料";
		}
		// 7. 資料檢查合理性
		if (h < 10 || h > 300 || w < 10 || w > 1000) {
			return "資料不正確, 超過合理範圍~~";
		}
		// 8.計算 BMI 並回傳
		double bmi = w / Math.pow(h / 100, 2);
		String output = String.format("身高:%.1f 體重:%.1f BMI:%.2f", h, w, bmi);
		return output;
	}

}
