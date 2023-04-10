package com.example.study_springbootvue.controller;

import com.example.study_springbootvue.entity.Cart;
import com.example.study_springbootvue.entity.Item;
import com.example.study_springbootvue.repository.CartRepository;
import com.example.study_springbootvue.repository.ItemRepository;
import com.example.study_springbootvue.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


@RestController
public class CartController {

    @Autowired
    JwtService jwtService;

    private final CartRepository cartRepository;
    private final ItemRepository itemRepository;

    public CartController(CartRepository cartRepository, ItemRepository itemRepository) {
        this.cartRepository = cartRepository;
        this.itemRepository = itemRepository;
    }

    @GetMapping("/api/cart/items")
    public ResponseEntity getcartItmes(@CookieValue(value = "token", required = false) String token){
        if(!jwtService.isValid(token)){
            throw  new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }

        int memberId = jwtService.getId(token);
        List<Cart> carts = cartRepository.findByMemberId(memberId);
        List<Integer> itemIds = carts.stream().map(Cart::getItemId).toList();
        List<Item> items = itemRepository.findByIdIn(itemIds);

        return new ResponseEntity(items, HttpStatus.OK);
    }

    @PostMapping ("/api/cart/items/{itemId}")
    public ResponseEntity pushCartItme(@PathVariable("itemId") int itemId,
        @CookieValue(value = "token", required = false) String token)
    {
        if(!jwtService.isValid(token)){
            throw  new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
        int memberId = jwtService.getId(token);
        Cart cart = cartRepository.findByMemberIdAndItemId(memberId, itemId);

        if(cart == null){
            Cart newCart = new Cart();
            newCart.setMemberId(memberId);
            newCart.setItemId(itemId);
            cartRepository.save(newCart);
        }

        return new ResponseEntity<>(HttpStatus.OK);

    }

    @DeleteMapping("/api/cart/items/{itemId}")
    public ResponseEntity removeCartItem(
            @PathVariable("itemId") int itemId,
            @CookieValue(value = "token", required = false) String token
    ){
        if(!jwtService.isValid(token)){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }

        int memberId = jwtService.getId(token);
        Cart cart = cartRepository.findByMemberIdAndItemId(memberId, itemId);

        cartRepository.delete(cart);

        return new ResponseEntity(HttpStatus.OK);
    }


}
