public class SortedRotated {
    public static int minimum(int arr[], int l, int r){
        //not rotated
        if(arr[l]<arr[r]) return arr[0];
        //single element
        if(arr[l]==arr[r]) return arr[l];
        int mid=(l+r)/2;
        //middle element is minimum
        if((mid == 0 || arr[mid-1]>arr[mid]) && arr[mid]<arr[mid+1]) return arr[mid];
        //left traversal
        if((mid == 0 || arr[mid]>arr[mid-1]) && arr[mid]<arr[mid+1])
            return minimum(arr,l,mid-1);
        //right traversal
        return minimum(arr,mid+1,r);
    }

    public static void main(String[] args) {
        int arr[]={5,4,3,2,1};
        int n=arr.length;
        int result=minimum(arr,0,n-1);
        System.out.println("The minimum number is : "+result);

    }
}