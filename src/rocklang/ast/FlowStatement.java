package rocklang.ast;

import rocklang.exception.RockException;
import rocklang.runtime.Environment;
import rocklang.runtime.Rock;
import rocklang.util.FormatStream;

import java.io.IOException;

import static rocklang.util.Utils.simplifyASTList;

public class FlowStatement extends ASTList {
    public FlowStatement(AST[] children) {
        super(children);
    }

    public AST statement() {
        return child(0);
    }

    @Override
    public Rock value(Environment env, Rock base) throws RockException {
        return statement().value(env, null);
    }

    @Override
    public AST simplify() {
        return new FlowStatement(simplifyASTList(children()));
    }

    @Override
    public void format(FormatStream fs) throws IOException {
        statement().format(fs);
        fs.print(";");
    }
}
