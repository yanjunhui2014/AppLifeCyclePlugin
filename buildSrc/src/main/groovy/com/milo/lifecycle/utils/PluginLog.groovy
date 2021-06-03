package com.milo.lifecycle.utils

class PluginLog {
    static def tag = "LifeCyclePlugin"

    static void i(String msg) {
        System.out.println("{##$tag##::$msg}")
    }

}