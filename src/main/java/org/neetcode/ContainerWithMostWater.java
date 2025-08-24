package org.neetcode;

public class ContainerWithMostWater {

    public int maxArea(int[] heights) {
        int len=heights.length;
        int area=0;
        for (int i=0; i<len; i++){
            for (int j=0; j<len; j++){
                if (i!=j){
                    int ver=heights[i]>heights[j]?heights[j]:heights[i];
                    int hor=i>j?i-j:j-i;
                    int prod=ver*hor;
                    area=area>prod?area:prod;
                }
            }
        }
        return area;
    }

}
