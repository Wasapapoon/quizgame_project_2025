package item.usage;

import java.util.List;

public interface hasHint {
	
	boolean getHasHint();

    void setHint(List<String> hint);

    List<String> getHint();
    
    boolean useHint();
    
    public void setUseHint(boolean use);
}
