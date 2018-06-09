package ws.exui.uibase;

public interface I_Transform {

	/**
	 * 将本视图的逻辑坐标点转化为实际坐标点
	 * @param point 逻辑坐标点
	 * @return 实际坐标点*/
	I_Point translate(I_Point point);

}
