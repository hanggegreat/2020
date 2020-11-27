package cn.lollipop.designpattern.command;

public class DeletePageCommand extends Command {
    private PageGroup pg = new PageGroup();

    @Override
    public void execute() {
        pg.find();
        pg.delete();
        pg.plan();
    }
}