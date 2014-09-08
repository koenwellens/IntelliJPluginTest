package be.koen.wellens;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.psi.PsiClass;

public class MyBuilderGeneratorAction extends AnAction {

    public void actionPerformed(AnActionEvent e) {
        final PsiClass psiClass = PsiClassFromContextExtractor.createPsiClassFrom(e);
        new MySimpleWriteCommandAction(psiClass).execute();
    }


}
