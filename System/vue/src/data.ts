
export interface Credit {
    credit: string;
    indicator: string;
    point: number;
}

export const CurriculumList: Credit[] = [
    {
        credit: 'Academic Courses',
        indicator: 'Institution offers sustainability course content as measured by the percentage of academic courses\n' +
            'offered that are sustainability-focused or sustainability-inclusive.',
        point: 14,
    },
    {
        credit: 'Learning Outcomes',
        indicator: 'This credit recognizes institutions that have adopted sustainability learning outcomes. Learning outcomes\n' +
            'help students develop specific sustainability knowledge and skills and provide institutions and accrediting\n' +
            'bodies with standards against which to assess student learning.',
        point: 8,
    },
    {
        credit: 'Undergraduate Program',
        indicator: 'This credit recognizes institutions that have formal, undergraduate-level degree programs focused on\n' +
            'sustainability. Developing such programs signals an institution’s commitment to sustainability.',
        point: 3,
    },
    {
        credit: 'Graduate Program',
        indicator: 'This credit recognizes institutions that have formal, graduate academic degree programs focused on\n' +
            'sustainability. Developing such programs signals an institution’s commitment to sustainability.',
        point: 3,
    },
    {
        credit: 'Immersive Experience',
        indicator: 'This credit recognizes institutions that offer sustainability-focused immersive experience programs.\n' +
            'Sustained immersive experiences such as community-based internships and “study abroad” programs\n' +
            'give students the opportunity to witness and learn in-depth about sustainability challenges and solutions.',
        point: 2,
    },
    {
        credit: 'Sustainability Literacy Assessment',
        indicator: 'This credit recognizes institutions that are assessing the sustainability literacy of their students. Such an\n' +
            'assessment helps institutions evaluate the success of their sustainability education initiatives and develop\n' +
            'insight into how these initiatives could be improved.',
        point: 4,
    },
    {
        credit: 'Incentives for Developing Courses',
        indicator: 'Institution offers sustainability course content as measured by the percentage of academic courses\n' +
            'offered that are sustainability-focused or sustainability-inclusive.',
        point: 2,
    },
    {
        credit: 'Campus as a Living Laboratory',
        indicator: 'This credit recognizes institutions that utilize their infrastructure and operations as living environments for\n' +
            'multidisciplinary learning and applied research that advances sustainability on campus. Students that\n' +
            'actively participate in making their campuses more sustainable are well prepared to continue that work in\n' +
            'their careers and communities after graduation.',
        point: 4,
    },
];

export const ResearchList: Credit[] = [
    {
        credit: 'Research and Scholarship',
        indicator: 'This credit recognizes institutions where employees are conducting research and other forms of\n' +
            'scholarship on sustainability topics. Conducting an inventory of an institution’s sustainability research can\n' +
            'serve as a valuable first step in identifying strengths and areas for development.',
        point: 12,
    },{
        credit: 'Support for Sustainability Research',
        indicator: 'This credit recognizes institutions that have programs in place to encourage students and academic staff\n' +
            'to research sustainability. Providing support and incentives demonstrates that sustainability is an\n' +
            'institutional priority and can help deepen students’ understanding of sustainability issues and attract new\n' +
            'researchers to the field.',
        point: 4,
    },{
        credit: 'Open Access to Research',
        indicator: 'This credit recognizes institutions that have repository programs and policies in place to facilitate open\n' +
            'access to new peer-reviewed research and scholarship. Institutions that empower academics to distribute\n' +
            'their scholarly writings freely help stimulate learning and innovation, and facilitate the translation of this\n' +
            'knowledge into public benefits that advance sustainability.',
        point: 2,
    },
];

export const CampusList: Credit[] = [
    {
        credit: 'Student Educators Program',
        indicator: 'This credit recognizes institutions with programs that engage students to serve as educators in\n' +
            'peer-to-peer sustainability outreach. Such initiatives, sometimes known as Eco-Reps programs, help\n' +
            'disseminate sustainability concepts and a sustainability ethic throughout the campus community.',
        point: 4,
    },{
        credit: 'Student Orientation',
        indicator: 'This credit recognizes institutions that include sustainability in orientation activities and programming.\n' +
            'Including sustainability in student orientation demonstrates that sustainability is an institutional goal and\n' +
            'encourages students to adopt sustainable habits in their new school environments.',
        point: 2,
    },{
        credit: 'Student Life',
        indicator: 'This credit recognizes institutions that have co-curricular programs and initiatives that contribute to\n' +
            'students learning about sustainability outside of the formal classroom. These programs and initiatives\n' +
            'engage students by integrating sustainability into their lives, experiential learning experiences, and\n' +
            'campus culture.',
        point: 2,
    },{
        credit: 'Outreach Materials and Publications',
        indicator: 'This credit recognizes institutions that produce outreach materials and publications that enhance student\n' +
            'learning about sustainability outside of the formal classroom.',
        point: 2,
    },{
        credit: 'Outreach Campaign',
        indicator: 'This credit recognizes institutions that hold sustainability outreach campaigns that yield measurable,\n' +
            'positive results in advancing the institution’s sustainability performance (e.g., a reduction in energy or\n' +
            'water consumption). Campaigns engage the campus community around sustainability issues and can\n' +
            'help raise student and employee awareness about sustainability.',
        point: 4,
    },{
        credit: 'Assessing Sustainability Culture',
        indicator: 'This credit recognizes institutions that are assessing the sustainability culture of the campus community.\n' +
            'Such assessments help institutions evaluate the success of their sustainability outreach and education\n' +
            'initiatives and develop insight into how these initiatives could be improved.',
        point: 1,
    },{
        credit: 'Employee Educators Program',
        indicator: 'This credit recognizes institutions that coordinate programs in which employees educate and mobilize\n' +
            'their peers around sustainability initiatives and programs. Engaging employees in peer educator roles can\n' +
            'help disseminate sustainability messages more widely and encourage broader participation in\n' +
            'sustainability initiatives.',
        point: 3,
    },{
        credit: 'Employee Orientation',
        indicator: 'Providing information and tools about the institution’s sustainability\n' +
            'programs and options at the time when an employee is getting acquainted with his or her new employer\n' +
            'and developing new work routines and habits can help encourage the adoption of environmentally and\n' +
            'socially preferable habits, routines, and choices.',
        point: 1,
    },{
        credit: 'Staff Professional Development and Training',
        indicator: 'This credit recognizes institutions that ensure that staff members have the opportunity to participate in\n' +
            'professional development and training opportunities in sustainability. By offering and supporting\n' +
            'professional development and training opportunities in sustainability to all staff members, an institution\n' +
            'helps equip its staff to implement sustainable practices and systems and to model sustainable behavior\n' +
            'for students and the rest of the campus community.',
        point: 2,
    },
];

export const PublicList: Credit[] = [
    {
        credit: 'Community Partnerships',
        indicator: 'This credit recognizes institutions that have developed campus-community partnerships to advance\n' +
            'sustainability. As community members and leaders, colleges and universities can be powerful catalysts,\n' +
            'allies, and partners in envisioning, planning, and acting to create a sustainable future in the region and\n' +
            'beyond.',
        point: 3,
    },{
        credit: 'Inter-Campus Collaboration',
        indicator: 'This credit recognizes institutions that collaborate with other colleges or universities to help build campus\n' +
            'sustainability broadly. Institutions can make significant contributions to sustainability by sharing their\n' +
            'experiences and expertise with other colleges and universities.',
        point: 3,
    },{
        credit: 'Continuing Education',
        indicator: 'This credit recognizes institutions that provide continuing education courses and programs in\n' +
            'sustainability to the community. Such courses train community members in sustainability topics and help\n' +
            'build knowledge about the subject.',
        point: 5,
    },{
        credit: 'Community Service',
        indicator: 'Volunteerism and the sense of compassion that community services help develop are fundamental to\n' +
            'achieving sustainability. From tutoring children to removing invasive species to volunteering at a food\n' +
            'bank, students and employees can make tangible contributions that address sustainability challenges\n' +
            'through community service.',
        point: 5,
    },{
        credit: 'Participation in Public Policy',
        indicator: 'This credit recognizes institutions that promote sustainability through public policy advocacy. There are\n' +
            'myriad public policies for which institutions can advocate that address sustainability, including policies\n' +
            'specific to higher education. Given the prominence and importance of colleges and universities in their\n' +
            'communities, institutions can be powerful voices in advancing sustainability through legislation and policy.',
        point: 2,
    },{
        credit: 'Trademark Licensing',
        indicator: 'This credit recognizes institutions that join a monitoring and verification organization to help ensure that\n' +
            'apparel bearing the institution’s name is produced under fair conditions. By ensuring that apparel bearing\n' +
            'the institution’s logo is made under fair working conditions, institutions promote health, safety, and secure\n' +
            'livelihoods for domestic and global workers.',
        point: 2,
    },
];

export const CPList: Credit[] = [
    {
        credit: 'Sustainability Coordination',
        indicator: 'This credit recognizes institutions with active committees, offices, or officers charged by the\n' +
            'administration or board of trustees to coordinate sustainability work on campus. Institution-wide\n' +
            'coordination helps institutions organize, implement, and publicize sustainability initiatives.',
        point: 1,
    },{
        credit: 'Sustainability Planning',
        indicator: 'This credit recognizes institutions that have developed comprehensive plans to move toward\n' +
            'sustainability. Sustainability planning affords an institution the opportunity to clarify its vision of a\n' +
            'sustainable future and provides a roadmap to help guide decision-making. Establishing measurable goals\n' +
            'and objectives allows an institution to track its future progress, identify and document its successes, and\n' +
            'manage the levels of resources devoted to (and required for) the attainment of its sustainability goals.\n' +
            'Including sustainability at a high level in the institution’s strategic plan and other guiding documents also\n' +
            'signals an institution’s commitment to sustainability and may help infuse an ethic of environmental, fiscal\n' +
            'and social responsibility throughout the campus community.',
        point: 4,
    },{
        credit: 'Inclusive and Participatory Governance',
        indicator: 'This credit recognizes institutions that engage campus and community stakeholders in the ongoing\n' +
            'governance of the college or university. Governance includes a variety of organizational functions and\n' +
            'decision-making processes, from financial oversight and personnel management to goal-setting and\n' +
            'strategic planning.',
        point: 3,
    },{
        credit: 'Reporting Assurance',
        indicator: 'This credit recognizes institutions that engage in a comprehensive data quality and assurance process\n' +
            'before submitting a STARS report. An assured report can provide campus stakeholders with a greater\n' +
            'sense of confidence in what is publicly reported, minimize reputational risks associated with inconsistent\n' +
            'data quality, and increase the value of sustainability reporting.',
        point: 1,
    },
];

export const InvestmentList: Credit[] = [
    {
        credit: 'Committee on Investor Responsibility',
        indicator: 'This credit recognizes institutions with an established and active committee on investor responsibility\n' +
            '(CIR) with multi-stakeholder representation. Establishing a CIR provides a structure for fostering dialogue\n' +
            'on investment decisions, and can help campuses make responsible investment decisions that promote\n' +
            'sustainability.',
        point: 2,
    },{
        credit: 'Sustainable Investment',
        indicator: 'This credit recognizes institutions that use their investment power to promote sustainability. There are a\n' +
            'variety of approaches an institution can take toward sustainable investment, including making positive\n' +
            'investments that promote sustainability and engaging with companies in which they already hold\n' +
            'investments.',
        point: 5,
    },{
        credit: 'Investment Disclosure',
        indicator: 'This credit recognizes institutions that regularly make their investment holdings publicly available. The\n' +
            'transparency ensured by public disclosure acts as an important accountability mechanism and as a\n' +
            'learning tool for students and other stakeholders.',
        point: 1,
    },
];

export const SEList: Credit[] = [
    {
        credit: 'Diversity and Equity Coordination',
        indicator: 'This credit recognizes institutions with active committees, offices, or officers charged by the\n' +
            'administration or governing body to coordinate diversity, equity, inclusion, and human rights work on\n' +
            'campus.',
        point: 2,
    },{
        credit: 'Assessing Diversity and Equity',
        indicator: 'This credit recognizes institutions that systemically assess diversity and equity on campus. Fostering an\n' +
            'inclusive and welcoming campus culture is important to ensuring the academic and social success of all\n' +
            'campus community members.',
        point: 1,
    },{
        credit: 'Support for Underrepresented Groups',
        indicator: 'This credit recognizes institutions that have programs in place to support underrepresented groups and\n' +
            'foster a more diverse and inclusive campus community. Certain challenges accompany being a minority\n' +
            'on campus.',
        point: 3,
    },{
        credit: 'Affordability and Access',
        indicator: 'This credit recognizes institutions that are affordable to low-income students. Achieving a post-secondary\n' +
            'degree is a valuable tool in addressing inequity, but in order for higher education to help society move\n' +
            'toward greater equity, schools must be accessible to low-income populations.',
        point: 4,
    },
];

export const WWList: Credit[] = [
    {
        credit: 'Employee Compensation',
        indicator: 'This credit recognizes institutions that ensure that their lowest paid workers earn a living wage. Poverty,\n' +
            'or the inability of current generations to meet their needs, is a sustainability challenge even in highly\n' +
            'developed countries. By providing employees with wages and benefits that are sufficient to meet basic\n' +
            'needs, a university or college and its contractors can enfranchise the entire campus workforce so that\n' +
            'each individual can contribute positively and productively to the community.',
        point: 3,
    },{
        credit: 'Assessing Employee Satisfaction',
        indicator: 'This credit recognizes institutions that support the engagement of their employees by conducting a\n' +
            'regular survey or other evaluation. Evaluating employee satisfaction and engagement helps institutions\n' +
            'gauge their performance as an employer and can identify strengths as well as areas for development.',
        point: 1,
    },{
        credit: 'Wellness Programs',
        indicator: 'This credit recognizes institutions that support the health and wellbeing of their employees and students.\n' +
            'Providing wellness programs and smoke-free environments can enhance the health and wellbeing of the\n' +
            'entire campus community.',
        point: 1,
    },{
        credit: 'Workplace Health and Safety',
        indicator: 'This credit recognizes institutions that help ensure the health and safety of their employees. Institutions\n' +
            'that work to minimize workplace injuries and occupational disease cases help ensure that all employees\n' +
            'enjoy a safe working environment and a socially responsible workplace.',
        point: 2,
    },
];

export const ILList: Credit[] = [
    {
        credit: 'Innovation and Leadership',
        indicator: 'The credits in this category recognize institutions that are seeking innovative solutions to sustainability\n' +
            'challenges and demonstrating sustainability leadership in ways that are not otherwise captured in\n' +
            'STARS.',
        point: 4,
    },
];

export const BGList: Credit[] = [
    {
        credit: '',
        indicator: '',
        point: 1,
    },
];

export const ECList: Credit[] = [
    {
        credit: '',
        indicator: '',
        point: 1,
    },
];

export const FDList: Credit[] = [
    {
        credit: '',
        indicator: '',
        point: 1,
    },
];

export const PWList: Credit[] = [
    {
        credit: '',
        indicator: '',
        point: 1,
    },
];

export const TransportationList: Credit[] = [
    {
        credit: '',
        indicator: '',
        point: 1,
    },
];
