package be.koen.wellens;

import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.psi.*;

    public class MySimpleWriteCommandAction extends WriteCommandAction.Simple {

        private PsiClass psiClass;

        public MySimpleWriteCommandAction(PsiClass psiClass) {
            super(psiClass.getProject(), psiClass.getContainingFile());
            this.psiClass = psiClass;
        }

        @Override
        protected void run() throws Throwable {
            PsiElementFactory psiElementFactory = JavaPsiFacade.getElementFactory(getProject());

            psiClass.add(createPsiFieldWith(psiElementFactory));
            psiClass.add(createPsiMethodWith(psiElementFactory));
            PsiClass innerClass = createPsiClassWith(psiElementFactory);
        }

        private PsiField createPsiFieldWith(PsiElementFactory elementFactory) {
        StringBuilder field = new StringBuilder();
        field.append("private NumberFactory numberFactory = new NumberFactory();");
        return elementFactory.createFieldFromText(field.toString(), psiClass);
    }

        private PsiMethod createPsiMethodWith(PsiElementFactory elementFactory) {
        StringBuilder method = new StringBuilder();
        method.append("public NumberFactory getNumberFactory() {\n");
        method.append("return this.numberFactory;\n");
        method.append("}\n");

        return elementFactory.createMethodFromText(method.toString(), psiClass);
    }

        private PsiClass createPsiClassWith(PsiElementFactory elementFactory) {
        PsiClass innerClass = elementFactory.createClass("NumberFactory");
        psiClass.add(innerClass);
        return innerClass;
    }
    }


