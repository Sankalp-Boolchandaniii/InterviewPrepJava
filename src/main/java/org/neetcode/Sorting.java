package org.neetcode;

public class Sorting {

    public static void main(String[] args) {
//        bubbleSort(new int[]{5,3,4,2,1});
//        selectionSort(new int[]{5,3,4,2,1});
//        insertionSort(new int[]{5,3,4,2,1});
        personalSort(new int[]{5,3,4,2,1});
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

    static void selectionSort(int[] arr){
        int n=arr.length;
        for (int i=0;i<n-1;i++){
            int smallestIdx=i;
            for (int j=i+1;j<n;j++){
                if (arr[j]<arr[smallestIdx]){
                    smallestIdx=j;
                }
            }
            int temp=arr[smallestIdx];
            arr[smallestIdx]=arr[i];
            arr[i]=temp;
        }
        for (int i:arr){
            System.out.println(i);
        }
    }

    static void insertionSort(int[] arr){
        int n=arr.length;
        for (int i=1;i<n;i++){
            int curr=arr[i];
            int prev=i-1;
            while (prev>=0 && arr[prev]>curr){
                arr[prev+1]=arr[prev];
                prev--;
            }
            arr[prev+1]=curr;
        }
        for (int i:arr){
            System.out.println(i);
        }
    }

    static void personalSort(int[] arr){
        int n=arr.length;
        for (int i=0;i<n-1;i++){
            for(int j=i+1; j<n;j++){
                if (arr[i]>arr[j]){
                    int temp=arr[i];
                    arr[i]=arr[j];
                    arr[j]=temp;
                }
            }
        }
        for (int i:arr){
            System.out.println(i);
        }
    }


}
