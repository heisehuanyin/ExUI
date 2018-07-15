package exui.binding;
/**
 * 每个实现了该接口的对象都可以用于数据集合之间的对等绑定，规定他的子数据类型同样是数据集合类型，属性类型则是简单类型*/

public interface I_Object4BindingCommon {
	/**
	 * 获取子数据集合，用于两个容器类型数据集合的绑定
	 * @return 内部数据集合*/
	I_ListCommon<I_Object4BindingCommon> getChildren();
	
	/**
	 * 通过属性名称获取属性，规定所有属性都是简单数值类型
	 * @return 特定属性对象*/
	I_ValueCommon getPropertyAsKey(String key);
}
