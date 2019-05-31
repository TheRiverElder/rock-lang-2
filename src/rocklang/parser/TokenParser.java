package rocklang.parser;

import rocklang.ast.AST;
import rocklang.ast.ASTFactory;
import rocklang.ast.ASTLeaf;
import rocklang.token.Token;
import rocklang.token.TokenStream;
import rocklang.token.TokenType;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TokenParser extends Parser {

    private TokenType type;
    private Set<String> allowedLiterals;

    public TokenParser(TokenType type, String... allowedLiterals) {
        this.type = type;
        if (allowedLiterals.length != 0) {
            this.allowedLiterals = new HashSet<>(Arrays.asList(allowedLiterals));
        }
        factory = ASTFactory.of(ASTLeaf.class);
    }

    public TokenParser(String... allowedLiterals) {
        this(TokenType.IDENTIFIER, allowedLiterals);
    }

    @Override
    public AST parse(TokenStream ts) {
        if (!ts.hasMore()) {
            return null;
        }
        Token token = ts.next();
        if (token.type() != type) {
            return null;
        }
        if (type == TokenType.IDENTIFIER && !allowedLiterals.contains(token.literal())) {
            return null;
        }
        ts.read();
        return create(token);
    }

    @Override
    public boolean parse(TokenStream ts, List<AST> scope) {
        AST ast = parse(ts);
        if (ast != null) {
            scope.add(ast);
            return true;
        } else {
            return false;
        }
    }
}
