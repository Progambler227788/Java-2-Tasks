
      //  Returns a string representation of this queue. 
    public int getmin() throws Exception{

        if (isEmpty())
            throw new Exception("Que is Empty"); 

        int result = Integer.MAX_VALUE;
        LinkedList current = front;
        while (current != null){
            if(current.getData() < result)
              result = current.getData();
        }
        return result;
    }
 