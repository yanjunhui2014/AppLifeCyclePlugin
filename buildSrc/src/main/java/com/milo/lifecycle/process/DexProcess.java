package com.milo.lifecycle.process;

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
public interface DexProcess {

    boolean shouldProcess(File file);

    void startProcess(File file);

}
