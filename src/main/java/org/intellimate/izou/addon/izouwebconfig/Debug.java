package org.intellimate.izou.addon.izouwebconfig;

import org.intellimate.izou.addon.AddOnModel;
import org.intellimate.izou.main.Main;

import java.util.LinkedList;

/**
 * Use this class to debug
 */
public class Debug {
    public static void main(String[] args) {
        LinkedList<AddOnModel> addOns = new LinkedList<>();

        WebConfigAddOn addOn = new WebConfigAddOn();
        addOns.add(addOn);

        Main main = new Main(addOns, true);


    }
}
