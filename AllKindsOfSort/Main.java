package Main;

import java.util.Arrays;

public class Main {
    public static void main(String[] arg) {
        int[] numarray1 = {73, 35, 45, 69, 12};
        int[] numarray2 = {35, 73, 69, 45, 12};
        int[] numarray3 = {69, 73, 35, 45, 12};
        int[] numarray4 = {12, 45, 35, 73, 69};
        int[] numarray5 = {12, 45, 73, 35, 69};
        //插入排序
        InsertSort(numarray1);
        System.out.println(Arrays.toString(numarray1));
        //快速排序
        QuickSort(numarray2, 0, numarray2.length - 1);
        System.out.println(Arrays.toString(numarray2));
        //归并排序
        int[] tmp = new int[numarray3.length];
        MergeSort(numarray3, 0, numarray3.length - 1, tmp);
        System.out.println(Arrays.toString(numarray3));
        //冒泡排序
        BubbleSort(numarray4);
        System.out.println(Arrays.toString(numarray4));
        //堆排序
        HeapSort(numarray5);
        System.out.println(Arrays.toString(numarray5));
    }

    //冒泡排序(顺序为:从小到大)
    //思路:
    //从无序数组中每2个进行比较排序,逐渐将最大值送到最后面去,一次便可以让一个数就位,那么只需要num.length-1次就可以
    //时间复杂度:
    //O(n²)
    public static void BubbleSort(int[] num) {
        for (int i = 0; i < num.length - 1; ++i) {
            for (int j = 0; j < num.length - 1; ++j) {
                if (num[j] > num[j + 1]) {
                    int temp = num[j];
                    num[j] = num[j + 1];
                    num[j + 1] = temp;
                }
            }
        }
    }

    //直接插入排序(顺序为:从小到大)
    //思路:
    //将一个记录插入已经排好序的有序表中,得到一个新的有序表
    //时间复杂度:
    //O(n²)
    public static void InsertSort(int[] num) {
        if (num == null || num.length < 2) {
            return;
        } else {
            int insertnumber;//即将插入的数字
            int checknumberindex;//与插入的数字进行比较的数值的下标
            for (int i = 1; i < num.length; ++i) {
                insertnumber = num[i];//把即将插入数值的数值存储下来
                checknumberindex = i - 1;//把已经排好序的最后一个数值的作为参考值
                while (insertnumber < num[checknumberindex] && checknumberindex >= 0) {
                    num[checknumberindex + 1] = num[checknumberindex];
                    if (checknumberindex > 0) {
                        checknumberindex--;//如果对比的下标依然大于0依次向前进行遍历
                    } else {
                        checknumberindex--;
                        break;
                    }//如果checknumberindex本来就已经等于0了,
                    // 说明这个需要插入的数就已经需要放在最前面了,就需要跳出循环了,而不是继续循环寻找更小的数
                }//循环判定结束的条件
                num[checknumberindex + 1] = insertnumber;//在对比值的之后放下需要插入的数
            }
        }
    }

    //快速排序(顺序为:从小到大)
    //思路:
    //先从右往左找一个小于基准的数，再从左往右找一个大于基准书的数，每一轮排序完成之后,左边的数都是小于基准数的,右边的数都是大于基准数
    //将快速排序的每一轮处理其实就是将这一轮的基准数归位，直到所有的数都归位为止，排序就结束了
    //时间复杂度:
    //O(NlogN)
    public static void QuickSort(int[] num, int begin, int end) {

        int left = begin;
        int right = end;
        //分别从左和从右进行遍历
        if (left > right) {
            return;
        }
        int checknumber = num[left];
        while (left < right) {
            while (left < right && num[right] >= checknumber) {
                right--;//如果右方的比checknumber更大时,right减小,因为符合顺序
            }//当跳出循环的时候, 便是一个右边的数小于checknumber 的时候
            num[left] = num[right];
            while (left < right && num[left] <= checknumber) {
                left++;//如果左方的比checknumber更小时,left增加,因为符合顺序
            }//当跳出循环的时候, 便是一个左边的数大鱼checknumber 的时候
            num[right] = num[left];
        }
        num[left] = checknumber;
        QuickSort(num, begin, right - 1);
        QuickSort(num, right + 1, end);
    }

    //合并排序(顺序为:从小到大)
    //思路:
    //把一个数组不断地划分，直到数组长度为1，然后将相邻两个有序子数组段合并为一个有序数组段。
    //时间复杂度:
    //O(NlogN)
    public static void MergeSort(int[] num, int low, int high, int[] tmp) {
        if (low < high) {
            int mid = (low + high) / 2;
            MergeSort(num, low, mid, tmp); //对左边序列进行归并排序
            MergeSort(num, mid + 1, high, tmp);  //对右边序列进行归并排序
            Merge(num, low, mid, high, tmp);    //合并两个有序序列
        }
    }

    public static void Merge(int[] num, int low, int mid, int high, int[] tmp) {
        int i = 0;
        int leftbegin = low, rightbegin = mid + 1;  //左边序列和右边序列起始索引
        while (leftbegin <= mid && rightbegin <= high) {
            if (num[leftbegin] < num[rightbegin]) {
                tmp[i] = num[leftbegin];
                i++;
                leftbegin++;
            } else {
                tmp[i] = num[rightbegin];
                i++;
                rightbegin++;
            }
        }
        //若左边序列还有剩余，则将其全部拷贝进tmp[]中
        while (leftbegin <= mid) {
            tmp[i++] = num[leftbegin++];
        }

        while (rightbegin <= high) {
            tmp[i++] = num[rightbegin++];
        }

        for (int t = 0; t < i; t++) {
            num[low + t] = tmp[t];
        }
    }

    //堆排序(顺序为:从小到大)
    //思路:
    //a.将无序的序列构建成一个堆，根据升序降序需求选择大顶堆或小顶堆;
    //b.将堆顶元素与末尾元素交换，将最大元素"沉"到数组末端;
    //c.重新调整结构，使其满足堆定义，然后继续交换堆顶元素与当前末尾元素，反复执行调整+交换步骤，直到整个序列有序。
    //时间复杂度:
    //O(NlogN)
    public static void sort(int[] arr) {
        //1.构建大顶堆
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            //从第一个非叶子结点从下至上，从右至左调整结构
            adjustHeap(arr, i, arr.length);
        }
        //2.调整堆结构+交换堆顶元素与末尾元素
        for (int j = arr.length - 1; j > 0; j--) {
            swap(arr, 0, j);//将堆顶元素与末尾元素进行交换
            adjustHeap(arr, 0, j);//重新对堆进行调整
        }
    }

    public static void adjustHeap(int[] arr, int i, int length) {
        int temp = arr[i];//先取出当前元素i
        for (int k = i * 2 + 1; k < length; k = k * 2 + 1) {//从i结点的左子结点开始，也就是2i+1处开始
            if (k + 1 < length && arr[k] < arr[k + 1]) {//如果左子结点小于右子结点，k指向右子结点
                k++;
            }
            if (arr[k] > temp) {//如果子节点大于父节点，将子节点值赋给父节点（不用进行交换）
                arr[i] = arr[k];
                i = k;
            } else {
                break;
            }
        }
        arr[i] = temp;//将temp值放到最终的位置
    }

    public static void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    public static void HeapSort(int[] num) {
        //1.构建大顶堆
        for (int i = num.length / 2 - 1; i >= 0; i--) {
            //从第一个非叶子结点从下至上，从右至左调整结构
            adjustHeap(num, i, num.length);
        }
        //2.调整堆结构+交换堆顶元素与末尾元素
        for (int j = num.length - 1; j > 0; j--) {
            swap(num, 0, j);//将堆顶元素与末尾元素进行交换
            adjustHeap(num, 0, j);//重新对堆进行调整
        }

    }
}
