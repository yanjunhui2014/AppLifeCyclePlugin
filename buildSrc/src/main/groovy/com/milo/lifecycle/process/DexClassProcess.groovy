package com.milo.lifecycle.process

class DexClassProcess implements DexProcess {


    @Override
    boolean shouldProcess(File file) {
        if(file == null)
            return false
        if(file.name.startsWith('R$'))
            return false
        if(file.name.startsWith('R.class'))
            return false
        return true
    }

    @Override
    void process(File file) {
//        if(!needProcess()){
//            return
//        }
        PluginLog.i("开始处理:$path")
    }

}