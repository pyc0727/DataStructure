package java.it.data;

public class Array<T> {
    //内部维护的私有数组
    private T[] data;
    //定义现有大小
    private int size;

    public int getSize(){
        return size;
    }

    public int getCapacity(){
        return data.length;
    }

    /**
     * 构造方法，传入容量即可。
     * @param capacity
     */
    public Array(int capacity){
        data =(T[]) new Object[capacity];
        size = 0;
    }

    /**
     * 空参构造方法，默认生成数组容量为16的数组
     */
    public Array(){
       this(16);
    }

    /**
     * 直接传入数组的构造方法
     * @param arr
     */
    public Array(T[] arr){
    data = arr;
    }

    /**
     * 查看数组是否为空
     * @return
     */
    public  boolean isEmpty(){
        return size==0;
    }

    /**
     * 在数组末尾添加一个元素
     * @param t
     * @throws Exception
     */
    public void addLast(T t)throws Exception{
        add(size,t);
    }

    /**
     * 在数组开始处添加一个元素
     * @param t
     * @throws Exception
     */
    public void addFirst(T t) throws  Exception{
        add(0,t);
    }

    /**
    * @Description: 根据指定角标来添加一个元素
    * @Param: [index, t]
    * @return: void
    */
    public void add(int index,T t)throws Exception{
        if(index>=size || index<0) {
            throw new Exception("插入角标违法，请检查后再输入");
        }
        if (this.getCapacity() == this.getSize()) {
            capactyInc(this.getCapacity()*2);
        }
        for (int i = index; i < size ; i++) {
            data[i+1]  = data[i];
        }
        data[index] = t;
        size++;
    }

    /**
    * @Description: 根据角标删除元素
    * @Param: [index]
    * @return: T
    */
    public T delete(int index) throws  Exception{
        if(index>= size|| index<0) {
            throw new Exception("角标位置不存在元素");
        }
        T t = data[index];
        for (int i = index; i < size ; i++) {
            data[i] = data[i+1];
        }
        size--;
        return  t;
    }


   /**
   * @Description: 删除第一个元素
   * @Param: []
   * @return: T
   */
    public T delFirst() throws  Exception{
         return delete(0);
    }

    /**
     * 移除最后一个元素,remove方法默认为移除最后的一个元素
     * @return
     * @throws Exception
     */
    public T remove() throws  Exception{
        return  delete(size-1);
    }

    /**
     * 修改相应角标的元素
     * @param index
     * @param t
     * @return
     * @throws Exception
     */
    public T set(int index,T t) throws  Exception{
        if(index>= size|| index<0) {
            throw new Exception("角标位置不存在元素");
        }
        T temp = data[index];
        data[index] = t;
        return temp;
    }

    /**
     * 根据角标来查询元素
     * @param index
     * @return
     * @throws Exception
     */
    public T get(int index) throws Exception{
        if(index>= size|| index<0) {
            throw new Exception("角标位置不存在元素");
        }
        return  data[index];
    }

    /**
     * 截取一个相应的集合
     * @param index
     * @param length
     * @return
     * @throws Exception
     */
    public T[] getArray(int index,int length)throws Exception{
        if(index>= size|| index<0) {
            throw new Exception("角标位置不存在元素");
        }
        if (length<0 || index+length>=size){
            throw  new Exception("需要的长度不存在，请重新确认长度");
        }
        T[] temp = (T[]) new Object[length];
        for (int i = 0; i < length; i++,index++) {
            temp[i] = data[index];
        }
        return  temp;
    }

    /**
     * @
     */
    public int getIndex(T t){
        //本想使用forEach循环,但包含结果也需要返回角标，所以使用for循环才能拿到他的角标
        for (int i = 0; i < size ; i++) {
            if(data[i].equals(t)){
                return  i;
            }
        }
        return -1;
    }

    /**
     * 判断是否包含某个元素
     * @param t
     * @return
     */
    public boolean contains(T t){
        for (int i = 0; i < size; i++) {
            if (data[i].equals(t)){
                return true;
            }
        }
        return  false;
    }

    /**
     * 在输入容量不够的情况下，自增容量
     * @param capacity
     */
    private void capactyInc(int capacity){
        T[] newData = (T[])new Object[capacity];
        for (int i = 0; i < size; i++) {
            newData[i] = data[i];
        }
        data = newData;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Array){
            Array arr = (Array) obj;
            for (int i = 0; i < arr.getSize(); i++) {
                try {
                    if (data[i].equals(arr.get(i)))
                        continue;
                    return false;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return true;
        }
        return false;
    }
}
