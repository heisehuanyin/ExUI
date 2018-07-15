package exui.binding;

import java.util.HashMap;
import java.util.Map;

public abstract class ExO4Binding implements I_Object4BindingCommon{
	private Map<String, I_ValueCommon> properties = new HashMap<>();
	
	public ExO4Binding() {
		
	}

	@Override
	public I_ValueCommon getPropertyAsKey(String key) {
		if(!this.properties.containsKey(key))
			return null;
		return this.properties.get(key);
	}
	
	/**
	 * 方便继承子类注册值属性
	 * @param valueKey 属性的名称，与代码中的属性名称一致
	 * @param value 属性实例*/
	protected void registProperties(String valueKey, I_ValueCommon value) {
		this.properties.put(valueKey, value);
	}
	

}
