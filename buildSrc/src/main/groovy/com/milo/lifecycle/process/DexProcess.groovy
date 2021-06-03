package com.milo.lifecycle.process

interface DexProcess {
    boolean shouldProcess(File file)

    void process(File file)
}