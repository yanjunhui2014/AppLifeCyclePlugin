package com.milo.lifecycle.process;

import com.milo.lifecycle.utils.PluginLog;

import java.io.File;

/**
 * Title：
 * Describe：
 * Remark：
 * <p>
 * Created by Milo
 * E-Mail : 303767416@qq.com
 * 6/3/21
 */
public class ClassDexProcess implements DexProcess {

    @Override
    public boolean shouldProcess(File file) {
        if (file == null)
            return false;
        if (file.getName().startsWith("R$") || file.getName().startsWith("R.class"))
            return false;
        return true;
    }

    @Override
    public void startProcess(File file) {
        PluginLog.i("开始处理" + file.getAbsolutePath());
    }

}
