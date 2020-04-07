package com.atguigu.pojo;

import java.math.BigDecimal;
import java.util.*;

public class Cart {

//    private List<CartItem> items = new ArrayList<CartItem>();

   private Map<Integer,CartItem> items = new LinkedHashMap<Integer, CartItem>();



    public void addItem(CartItem cartItem){
        CartItem item = items.get(cartItem.getId());
        if (item == null) {
            items.put(cartItem.getId(), cartItem);
        }else {
            item.setCount(cartItem.getCount()+1);
            item.setTotalPrice(item.getPrice().multiply(new BigDecimal(item.getCount())));
        }

        /*for (CartItem temp : items){
            if (temp.getId().equals(cartItem.getId())){
                temp.setCount(temp.getCount()+1);
                temp.setTotalPrice(temp.getPrice().multiply(new BigDecimal(temp.getCount())));
                return;
            }

        }
        items.add(cartItem);*/
    }

    public void clear(){
        items.clear();
    }

    public void deleteItem(Integer id){
        items.remove(id);
    }

    public BigDecimal updateCount(Integer id, Integer count){
        CartItem cartItem = items.get(id);
        if (cartItem !=null){
            cartItem.setCount(count);
            cartItem.setTotalPrice(cartItem.getPrice().multiply(new BigDecimal(cartItem.getCount())));
        }
        return null;
    }




    public Integer getTotalCount() {

        Integer totalCount = 0;
        for (Map.Entry<Integer,CartItem> entry : items.entrySet()){
            totalCount += entry.getValue().getCount();
        }


        return totalCount;
    }


    public BigDecimal getTotalPrice() {
        BigDecimal totalPrice = new BigDecimal(0);
        for (Map.Entry<Integer,CartItem> entry : items.entrySet()){
            totalPrice = totalPrice.add(entry.getValue().getTotalPrice());
        }

        return totalPrice;
    }


    public Map<Integer,CartItem> getItems() {
        return items;
    }
    public void setItems(Map<Integer, CartItem> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "totalCount=" + getTotalCount() +
                ", totalPrice=" + getTotalPrice() +
                ", items=" + items +
                '}';
    }
}
