package rocklang.ast;

import rocklang.exception.RockException;
import rocklang.runtime.Environment;
import rocklang.runtime.Rock;
import rocklang.token.Token;
import rocklang.util.FormatStream;

import java.io.IOException;

public class ASTList implements AST {

    private AST[] children;

    public ASTList(AST[] children) {
        this.children = children;
    }

    @Override
    public boolean isLeaf() {
        return false;
    }

    @Override
    public AST[] children() {
        return children;
    }

    @Override
    public AST child(int index) {
        return children[index];
    }

    @Override
    public int childCount() {
        return children.length;
    }

    @Override
    public Token token() {
        return null;
    }

    @Override
    public Rock value(Environment env, Rock base) throws RockException {
        return null;
    }

    @Override
    public Rock assign(Environment env, Rock base, Rock value) throws RockException {
        return null;
    }

    @Override
    public AST simplify() {
        return this;
    }

    @Override
    public void format(FormatStream fs) throws IOException {
        for (AST ast : children) {
            ast.format(fs);
            fs.newLine();
        }
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("( ");
        for (int i = 0; i < childCount(); i++) {
            builder.append(child(i));
            if (i != childCount() - 1) {
                builder.append(" ");
            }
        }
        return builder.append(" )").toString();
    }
}
