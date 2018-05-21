/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.rest_producer;

/**
 *
 * @author shivaraj
 */
public class Driver {
    public static void main(String args[]){
       new Rest_Producer().produce();
       new Rest_Consumer().consume();
    }
}
