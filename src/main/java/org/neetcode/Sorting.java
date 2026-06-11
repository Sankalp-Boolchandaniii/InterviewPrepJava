package org.neetcode;

public class Sorting {

    public static void main(String[] args) {
        bubbleSort(new int[]{5,3,4,2,1});
    }

    static void bubbleSort(int[] arr){
        int n=arr.length;
        for (int i=0;i<n-1;i++){
            boolean swap=false;
            for (int j=0;j<n-i-1;j++){
                if (arr[j]>arr[j+1]){
                    int temp=arr[j];
                    arr[j]=arr[j+1];
                    arr[j+1]=temp;
                    swap=true;
                }
            }
            if (!swap) break;
        }
        for (int i:arr)
            System.out.println(i);
    }

}
