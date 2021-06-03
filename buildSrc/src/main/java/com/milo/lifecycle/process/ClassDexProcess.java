package com.milo.lifecycle.process;

import com.milo.lifecycle.utils.PluginLog;
import com.milo.lifecycle.utils.Utils;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Arrays;

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
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(file);
            ClassReader classReader = new ClassReader(fis);

            if (!Utils.isActivity(classReader.getSuperName().replaceAll("/", "."))) {
                return;
            }
            PluginLog.i(String.format("%s是Activity", file.getName()));

            ClassWriter classWriter = new ClassWriter(classReader, ClassWriter.COMPUTE_MAXS);
            ClassVisitor classVisitor = new ClassVisitor(Opcodes.ASM5, classWriter) {

                @Override
                public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
                    super.visit(version, access, name, signature, superName, interfaces);
                }

                @Override
                public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
                    if ("onCreate".equals(name) && "(Landroid/os/Bundle;)V".equals(desc)) {

                    }

                    return super.visitMethod(access, name, desc, signature, exceptions);
                }
            };
            classReader.accept(classVisitor, ClassReader.EXPAND_FRAMES);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Utils.close(fis);
        }
    }

}
