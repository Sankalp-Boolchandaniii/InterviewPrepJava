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

//    classic 2 pointer question
//    start one pointer from the beginning of the array and one from the end - left and right
//    if height of the beginning pointer is less than height of the end pointer, increase the end pointer by one(l++)
//    else decrease the end pointer(r--)
//    do this until both the pointers are equal or have crossed each other
//    the below solution(better) has time complexity of O(n) and the above has a time complexity of O(n^2)

    public int maxAreaBetter(int[] heights) {
        int n=heights.length;
        int l=0;
        int r=n-1;
        int area=0;
        while (l<r){
            int ver=heights[l]<heights[r]?heights[l]:heights[r];
            int hor=r-l;
            int curArea=ver*hor;
            area=area>curArea?area:curArea;
            if (heights[l]<heights[r]){
                l++;
            } else {
                r--;
            }
        }
        return area;
    }

}
