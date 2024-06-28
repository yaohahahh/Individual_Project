<template>
  <div>
    <h1>{{ data.name }} </h1>
    <h2>{{data.full_name}}</h2>


    <div class="card" style="margin-bottom: 10px">
      <el-descriptions direction="vertical" :column="4" :size="size" border>
        <el-descriptions-item label="Rating">
          <el-tag type="info">{{ data.rating }}</el-tag>
        </el-descriptions-item>
        <el-descriptions-item label="Score">{{ data.score }}</el-descriptions-item>
        <el-descriptions-item label="Submitted Date">
        </el-descriptions-item>

      </el-descriptions>
    </div>

    <div class="card" style="margin-bottom: 10px">
      <div class="demo-collapse">
        <el-collapse v-model="activeNames" @change="handleChange">
          <template v-if="data.categories && data.categories.length" v-for="category in data.categories" :key="category.id">
            <el-collapse-item :title="getImpactAreaTitle(category.name, category.impact_areas)" :name="category.id">
              <el-table :data="getImpactAreaList(category.name, category.impact_areas)" stripe style="width: 100%">
                <el-table-column prop="credit" label="Credit" width="160" />
                <el-table-column prop="indicator" label="Indicator" />
                <el-table-column prop="point" label="Point" width="60" />
              </el-table>
            </el-collapse-item>
          </template>
          <template v-else>
            <p>No categories available.</p>
          </template>
        </el-collapse>
      </div>
    </div>




<!--
    <div class="card" style="margin-bottom: 10px">

      <div class="card" style="margin-bottom: 10px">
        <h3>Society</h3>
        <div class="demo-collapse">

          <el-collapse v-model="activeNames" @change="handleChange">
            <el-collapse-item title="Academics" name="1">

              <el-collapse-item title="Curriculum /40.0" name="1-1">

                <el-table :data="CurriculumList" stripe style="width: 100%" >
                  <el-table-column prop="credit" label="Credit" width="160" />
                  <el-table-column prop="indicator" label="Indicator"  />
                  <el-table-column prop="point" label="Point" width="60"/>
                </el-table>
              </el-collapse-item>

              <el-collapse-item title="Research point/18.0" name="1-2">
                <el-table :data="ResearchList" stripe style="width: 100%" >
                  <el-table-column prop="credit" label="Credit" width="160" />
                  <el-table-column prop="indicator" label="Indicator"  />
                  <el-table-column prop="point" label="Point" width="60"/>
                </el-table>
              </el-collapse-item>
            </el-collapse-item>
          </el-collapse>

          <el-collapse v-model="activeNames" @change="handleChange">
            <el-collapse-item title="Engagement" name="2">
              <el-collapse-item title="Campus Engagement point/21.0" name="2-1">
                <el-table :data="CampusList" stripe style="width: 100%" >
                  <el-table-column prop="credit" label="Credit" width="160" />
                  <el-table-column prop="indicator" label="Indicator"  />
                  <el-table-column prop="point" label="Point" width="60"/>
                </el-table>
              </el-collapse-item>

              <el-collapse-item title="Public Engagement point/20.0" name="2-2">
                <el-table :data="PublicList" stripe style="width: 100%" >
                  <el-table-column prop="credit" label="Credit" width="160" />
                  <el-table-column prop="indicator" label="Indicator"  />
                  <el-table-column prop="point" label="Point" width="60"/>
                </el-table>
              </el-collapse-item>
            </el-collapse-item>

          </el-collapse>

        </div>
      </div>

      <div class="card" style="margin-bottom: 10px">
        <h3>Economy</h3>
        <el-collapse v-model="activeNames" @change="handleChange">
          <el-collapse-item title="Planning & Administration" name="3">

            <el-collapse-item title="Coordination & Planning point/40.0" name="3-1">
              <el-table :data="CPList" stripe style="width: 100%" >
                <el-table-column prop="credit" label="Credit" width="160" />
                <el-table-column prop="indicator" label="Indicator"  />
                <el-table-column prop="point" label="Point" width="60"/>
              </el-table>
            </el-collapse-item>

            <el-collapse-item title="Investment point/18.0" name="3-2">
              <el-table :data="InvestmentList" stripe style="width: 100%" >
                <el-table-column prop="credit" label="Credit" width="160" />
                <el-table-column prop="indicator" label="Indicator"  />
                <el-table-column prop="point" label="Point" width="60"/>
              </el-table>
            </el-collapse-item>

            <el-collapse-item title="Social Equity point/40.0" name="3-3">
              <el-table :data="SEList" stripe style="width: 100%" >
                <el-table-column prop="credit" label="Credit" width="160" />
                <el-table-column prop="indicator" label="Indicator"  />
                <el-table-column prop="point" label="Point" width="60"/>
              </el-table>
            </el-collapse-item>

            <el-collapse-item title="Wellbeing & Work point/18.0" name="3-4">
              <el-table :data="WWList" stripe style="width: 100%" >
                <el-table-column prop="credit" label="Credit" width="160" />
                <el-table-column prop="indicator" label="Indicator"  />
                <el-table-column prop="point" label="Point" width="60"/>
              </el-table>
            </el-collapse-item>
          </el-collapse-item>
        </el-collapse>

        <el-collapse v-model="activeNames" @change="handleChange">
          <el-collapse-item title="Innovation & Leadership" name="4">
            <el-collapse-item title="Innovation & Leadership point/21.0" name="4-1">
              <el-table :data="ILList" stripe style="width: 100%" >
                <el-table-column prop="credit" label="Credit" width="160" />
                <el-table-column prop="indicator" label="Indicator"  />
                <el-table-column prop="point" label="Point" width="60"/>
              </el-table>
            </el-collapse-item>

          </el-collapse-item>

        </el-collapse>

      </div>

      <div class="card" style="margin-bottom: 10px">
        <h3>Environment</h3>
        <el-collapse v-model="activeNames" @change="handleChange">
          <el-collapse-item title="Operations" name="5">
            <el-collapse-item title="Buildings & Grounds point/21.0" name="5-1">
              <el-table :data="BGList" stripe style="width: 100%" >
                <el-table-column prop="credit" label="Credit" width="160" />
                <el-table-column prop="indicator" label="Indicator"  />
                <el-table-column prop="point" label="Point" width="60"/>
              </el-table>
            </el-collapse-item>
            <el-collapse-item title="Energy & Climate point/40.0" name="5-2">
              <el-table :data="ECList" stripe style="width: 100%" >
                <el-table-column prop="credit" label="Credit" width="160" />
                <el-table-column prop="indicator" label="Indicator"  />
                <el-table-column prop="point" label="Point" width="60"/>
              </el-table>
            </el-collapse-item>

            <el-collapse-item title="Food & Dining point/18.0" name="5-3">
              <el-table :data="FDList" stripe style="width: 100%" >
                <el-table-column prop="credit" label="Credit" width="160" />
                <el-table-column prop="indicator" label="Indicator"  />
                <el-table-column prop="point" label="Point" width="60"/>
              </el-table>
            </el-collapse-item>
            <el-collapse-item title="Procurement & Waste point/40.0" name="5-4">
              <el-table :data="PWList" stripe style="width: 100%" >
                <el-table-column prop="credit" label="Credit" width="160" />
                <el-table-column prop="indicator" label="Indicator"  />
                <el-table-column prop="point" label="Point" width="60"/>
              </el-table>
            </el-collapse-item>

            <el-collapse-item title="Transportation point/18.0" name="5-5">
              <el-table :data="TransportationList" stripe style="width: 100%" >
                <el-table-column prop="credit" label="Credit" width="160" />
                <el-table-column prop="indicator" label="Indicator"  />
                <el-table-column prop="point" label="Point" width="60"/>
              </el-table>
            </el-collapse-item>

          </el-collapse-item>

        </el-collapse>


      </div>
    </div>
-->
  </div>
</template>

<script setup lang="ts">
import request from "../../utils/request";
import { reactive, onMounted, ref, computed, watch } from 'vue';
import { useRoute } from 'vue-router';


const route = useRoute();
const id = route.params.id;

const data = reactive({
  name: '',
  full_name: '',
  version: null,
  total_version: null,
  score: null,
  rating: '',
  tableData: [],
  categories: [] as any[],
  form: {}
});


// Load data function
const load = () => {
  request.get(`/list/institution/${id}`)
      .then((res) => {

        data.name = res.data?.name;
        data.full_name = res.data?.full_name;

        data.rating = res.data?.rating;
        data.score = res.data?.score;
        data.categories = res.data?.categories || [];

      })
      .catch((error) => {
        console.error('Error loading data:', error);
      });
};

// Collapse panel
const activeNames = ref(['1']);

const handleChange = (val: string | string[]) => {
  console.log(val);
};

// List
const size = ref('default');

const getImpactAreaTitle = (categoryName: string, impactAreas: any[]) => {
  const impactArea = impactAreas.find(area => area.name === categoryName);
  if (impactArea) {
    return `${categoryName} “point”/${impactArea.point}`;
  }
  return categoryName; // Fallback if impactArea is not found (though it should be found based on your data structure)
};


onMounted(load);




const getImpactAreaList = (categoryName: string, impactAreaName: string) => {
  switch (categoryName) {
    case 'Academics':
      switch (impactAreaName) {
        case 'Curriculum':
          return CurriculumList;
        case 'Research':
          return ResearchList;
          // Add cases for other impact areas within Academics if needed
        default:
          return [];
      }
    case 'Engagement':
      switch (impactAreaName) {
        case 'Campus Engagement':
          return CampusList;
        case 'Public Engagement':
          return PublicList;
          // Add cases for other impact areas within Engagement if needed
        default:
          return [];
      }
      // Add cases for other categories if needed
    default:
      return [];
  }
};

interface Credit {
  credit: string;
  indicator: string;
  point: number;
}

const CurriculumList: Credit[] = [
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

const ResearchList: Credit[] = [
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

const CampusList: Credit[] = [
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

const PublicList: Credit[] = [
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

const CPList: Credit[] = [
  {
    credit: '',
    indicator: '',
    point: 1,
  },
];

const InvestmentList: Credit[] = [
  {
    credit: '',
    indicator: '',
    point: 1,
  },
];

const SEList: Credit[] = [
  {
    credit: '',
    indicator: '',
    point: 1,
  },
];

const WWList: Credit[] = [
  {
    credit: '',
    indicator: '',
    point: 1,
  },
];

const ILList: Credit[] = [
  {
    credit: '',
    indicator: '',
    point: 1,
  },
];

const BGList: Credit[] = [
  {
    credit: '',
    indicator: '',
    point: 1,
  },
];

const ECList: Credit[] = [
  {
    credit: '',
    indicator: '',
    point: 1,
  },
];

const FDList: Credit[] = [
  {
    credit: '',
    indicator: '',
    point: 1,
  },
];

const PWList: Credit[] = [
  {
    credit: '',
    indicator: '',
    point: 1,
  },
];

const TransportationList: Credit[] = [
  {
    credit: '',
    indicator: '',
    point: 1,
  },
];


</script>
