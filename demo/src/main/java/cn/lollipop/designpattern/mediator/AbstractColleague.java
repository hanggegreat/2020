package cn.lollipop.designpattern.mediator;

/**
 * @author lollipop
 * @date 2020/11/27 13:32:50
 */
public abstract class AbstractColleague {
    protected AbstractMediator mediator;

    public AbstractColleague(AbstractMediator mediator) {
        this.mediator = mediator;
    }
}
