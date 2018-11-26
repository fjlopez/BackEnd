package bluebomb.urlshortener.model;

public class Size{
    private Double height;
    private Double width;

    public Size(Double height, double width){
        this.height=height;
        this.width=width;
    }

    public Double getHeight(){
        return height;
    }

    public Double getWidth(){
        return width;
    }

    public void setHeight(Double height){
       this.height=height; 
    }
    
    public void setWidth(Double width){
        this.width=width; 
     }

}