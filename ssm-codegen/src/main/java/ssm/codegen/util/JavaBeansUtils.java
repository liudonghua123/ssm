package ssm.codegen.util;

/**
 * @author Jeff Butler
 */
public final class JavaBeansUtils {

    private JavaBeansUtils() {
        super();
    }

    /**
     * JavaBeans rules: <br />
     * eMail > seteMail() <br />
     * firstName > setFirstName() <br />
     * URL > setURL() <br />
     * XAxis > setXAxis() <br />
     * a > setA() <br />
     * B > invalid - this method assumes that this is not the case. Call getValidPropertyName first. <br />
     * Yaxis > invalid - this method assumes that this is not the case. Call getValidPropertyName first.
     */
    public static String getSetterMethodName(String property) {
        StringBuilder sb = new StringBuilder();

        sb.append(property);
        if (Character.isLowerCase(sb.charAt(0))) {
            if (sb.length() == 1 || !Character.isUpperCase(sb.charAt(1))) {
                sb.setCharAt(0, Character.toUpperCase(sb.charAt(0)));
            }
        }

        sb.insert(0, "set"); //$NON-NLS-1$

        return sb.toString();
    }

    public static String getCamelCaseString(String inputString, boolean firstCharacterUppercase) {
        StringBuilder sb = new StringBuilder();

        boolean nextUpperCase = false;
        for (int i = 0; i < inputString.length(); i++) {
            char c = inputString.charAt(i);

            switch (c) {
                case '_':
                case '-':
                case '@':
                case '$':
                case '#':
                case ' ':
                    if (sb.length() > 0) {
                        nextUpperCase = true;
                    }
                    break;

                default:
                    if (nextUpperCase) {
                        sb.append(Character.toUpperCase(c));
                        nextUpperCase = false;
                    } else {
                        sb.append(Character.toLowerCase(c));
                    }
                    break;
            }
        }

        if (firstCharacterUppercase) {
            sb.setCharAt(0, Character.toUpperCase(sb.charAt(0)));
        }

        return sb.toString();
    }

}
