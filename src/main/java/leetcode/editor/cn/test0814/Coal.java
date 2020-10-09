package leetcode.editor.cn.test0814;

public class Coal implements Cloneable{

    private String name;//煤种名称
    private Integer type=0;//煤种类型 0：煤场现有 1：国内长协 2：进口长协 3：国内现货 4：进口现货
    private Integer isPart = 0;//是否分仓 选择分仓的就可以从0吨开始  0：不分仓 1：分仓 默认不分仓
    private double calorific;//热值
    private double sulfur;//硫份
    private double price = 0;//平仓价（单价）
    private double stock = 0;//煤场存量
    private double transFare = 0;//运费 每吨
    private double unloadFare = 0;//卸煤费
    private double otherFare = 0;//其他费用
    private double amount = 0;//船的吨位数 也是最小购买量 默认为0 由用户界面输入


    public Coal() {
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getIsPart() {
        return isPart;
    }

    public void setIsPart(Integer isPart) {
        this.isPart = isPart;
    }

    public double getCalorific() {
        return calorific;
    }

    public void setCalorific(double calorific) {
        this.calorific = calorific;
    }

    public double getSulfur() {
        return sulfur;
    }

    public void setSulfur(double sulfur) {
        this.sulfur = sulfur;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getStock() {
        return stock;
    }

    public void setStock(double stock) {
        this.stock = stock;
    }

    public double getTransFare() {
        return transFare;
    }

    public void setTransFare(double transFare) {
        this.transFare = transFare;
    }

    public double getUnloadFare() {
        return unloadFare;
    }

    public void setUnloadFare(double unloadFare) {
        this.unloadFare = unloadFare;
    }

    public double getOtherFare() {
        return otherFare;
    }

    public void setOtherFare(double otherFare) {
        this.otherFare = otherFare;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Coal{" +
                "name='" + name + '\'' +
                ", type=" + type +
                ", isPart=" + isPart +
                ", calorific=" + calorific +
                ", sulfur=" + sulfur +
                ", price=" + price +
                ", stock=" + stock +
                ", transFare=" + transFare +
                ", unloadFare=" + unloadFare +
                ", otherFare=" + otherFare +
                ", amount=" + amount +
                '}';
    }

    public Object clone(){
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }

}
