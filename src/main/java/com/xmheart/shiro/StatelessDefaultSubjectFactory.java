package com.xmheart.shiro;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.subject.SubjectContext;
import org.apache.shiro.web.mgt.DefaultWebSubjectFactory;

/**
* 改造DefaultSubjectFactory，使得不创建session
* 
* @author x1ny
* @date 2017年5月22日
*/
public class StatelessDefaultSubjectFactory extends DefaultWebSubjectFactory {

	/* (non-Javadoc)
	 * @see org.apache.shiro.web.mgt.DefaultWebSubjectFactory#createSubject(org.apache.shiro.subject.SubjectContext)
	 */
	@Override
	public Subject createSubject(SubjectContext context) {  
        //不创建session  
        context.setSessionCreationEnabled(true);  
        return super.createSubject(context);  
    }
}
